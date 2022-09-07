package duke;

import duke.controller.MainWindow;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * Abstraction of all commands possibly received on duke,
 * and executes all commands.
 * @author Shaune Ang
 */
public class Command {
    static final String EXITWORD = "bye";
    private TaskList taskList;
    private FileLoaderSaver storage;
    private String fullCommand;

    private CommandTypes commandType;

    // enum of all command types
    public enum CommandTypes {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
    }

    /**
     * Constructor for command objects
     * @param commandType type of command this command with execute
     * @param fullCommand full user input command in String
     */
    public Command (CommandTypes commandType, String fullCommand) {
        this.commandType = commandType;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes tasks based on the command type of the command
     * @param taskList list containing all the current tasks
     * @param storage duke.FileLoaderSaver object for saving tasks after creating, deletion or manipulation
     * @throws IOException
     * @throws Exception
     */
    public String execute(TaskList taskList, FileLoaderSaver storage) throws IOException, Exception {
        this.taskList = taskList;
        this.storage = storage;
        String response = "";

        switch (commandType) {
            case LIST:
                response += taskList.listTasks();
                break;
            case TODO:
                response += addToDo(fullCommand) + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case DEADLINE:
                response += addDeadline(fullCommand) + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case EVENT:
                response += addEvent(fullCommand) + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case MARK:
            case UNMARK:
                response += changeMark(fullCommand);
                storage.writeToFile(taskList.createTxtFile());
                break;
            case DELETE:
                response += deleteTask(fullCommand);
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case FIND:
                response += findTask(fullCommand);
                break;
        }
        return response;
    }

    /**
     * Changes isCompleted of the task according to index given
     *
     * @param command change mark command from user
     */
    private String changeMark(String command) throws Exception {
        String[] splitComm = command.split(" ");
        String action = splitComm[0];
        int index = Integer.parseInt(splitComm[1]) - 1;

        if (action.equals("mark")) {
            return taskList.complete(index);
        } else if (action.equals("unmark")){
            return taskList.incomplete(index);
        } else {
            throw new Exception("Input Error.");
        }
    }

    /**
     * Deletes task according to index given
     *
     * @param command delete task command from user
     */
    private String deleteTask(String command) {
        String[] splitComm = command.split(" ");
        int index = Integer.parseInt(splitComm[1]) - 1;

        return taskList.delete(index);
    }

    /**
     * Creates a duke.task.ToDos instance and adds it to duke.ToDoList
     *
     * @param command todo command from user
     * @throws Exception
     */
    private String addToDo(String command) throws Exception {
        if (!command.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = command.substring(command.indexOf(" ") + 1);

        return taskList.addTask(new ToDos(name));
    }

    /**
     * Creates a duke.task.Deadline instance and adds it to duke.ToDoList
     *
     * @param command deadline command from user
     * @throws Exception
     */
    private String addDeadline(String command) throws Exception {
        if (!command.matches("deadline \\S.*")) {
            throw new Exception("The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new Exception("The description is missing a deadline.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        return taskList.addTask(new Deadline(name, deadline));
    }

    /**
     * Creates a duke.task.Event instance and adds it to duke.ToDoList
     *
     * @param command event command from user
     * @throws Exception
     */
    private String addEvent(String command) throws Exception {
        if (!command.matches("event \\S.*")) {
            throw new Exception("The description of an event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new Exception("The description is missing a time.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        return taskList.addTask(new Event(name, time));
    }

    /**
     * Finds tasks whose name matches search
     * @param command
     * @throws Exception
     */
    private String findTask(String command) throws Exception {
        String searchString = command.substring(command.indexOf(" ") + 1);
        return taskList.findTasks(searchString);
    }

    public static class DialogBox extends HBox {

        @FXML
        private Label dialog;
        @FXML
        private ImageView displayPicture;


        private DialogBox(String text, Image img) {
            Circle clip = new Circle(img.getWidth()/3, img.getWidth()/3,50);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
                fxmlLoader.setController(this);
                fxmlLoader.setRoot(this);
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            dialog.setText(text);
            displayPicture.setImage(img);
            displayPicture.setClip(clip);
            this.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
        }

        /**
         * Flips the dialog box such that the ImageView is on the left and text on the right.
         */
        private void flip() {
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            Collections.reverse(tmp);
            getChildren().setAll(tmp);
            setAlignment(Pos.TOP_LEFT);
            this.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, null, null)));
        }

        public static DialogBox getUserDialog(String text, Image img) {
            return new DialogBox(text, img);
        }

        public static DialogBox getDukeDialog(String text, Image img) {
            var db = new DialogBox(text, img);
            db.flip();
            return db;
        }
    }
}
