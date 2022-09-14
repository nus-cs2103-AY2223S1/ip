package duke;

import duke.controller.MainWindow;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
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
    private TaskList taskList;
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
        PRIORITY
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
        String response = "";

        switch (commandType) {
            case LIST:
                response += taskList.listTasks();
                break;
            case TODO:
                response += addToDo() + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case DEADLINE:
                response += addDeadline() + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case EVENT:
                response += addEvent() + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case MARK: // fallthrough
            case UNMARK:
                response += changeMark();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case DELETE:
                response += deleteTask() + "\n";
                response += taskList.displayListSize();
                storage.writeToFile(taskList.createTxtFile());
                break;
            case FIND:
                response += findTask();
                break;
            case PRIORITY:
                response += setTaskPriority();
                storage.writeToFile(taskList.createTxtFile());
                break;
            default:
                response = "I am sorry, I do not comprehend such commands. Please Try again...";

        }
        return response;
    }

    /**
     * Changes isCompleted of the task according to index given
     */
    private String changeMark() throws Exception {
        String[] splitComm = fullCommand.split(" ");
        assert splitComm.length == 2;
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
     */
    private String deleteTask() {
        String[] splitComm = fullCommand.split(" ");
        assert splitComm.length == 2;
        int index = Integer.parseInt(splitComm[1]) - 1;

        return taskList.delete(index);
    }

    /**
     * Creates a duke.task.ToDos instance and adds it to duke.ToDoList
     *
     * @throws Exception
     */
    private String addToDo() throws Exception {
        if (!fullCommand.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = fullCommand.substring(fullCommand.indexOf(" ") + 1);

        return taskList.addTask(new ToDo(name));
    }

    /**
     * Creates a duke.task.Deadline instance and adds it to duke.ToDoList
     *
     * @throws Exception
     */
    private String addDeadline() throws Exception {
        if (!fullCommand.matches("deadline \\S.*")) {
            throw new Exception("The description of a deadline cannot be empty.");
        } else if (!fullCommand.contains("/by")) {
            throw new Exception("The description is missing a deadline.");
        }

        String details = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        String name = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        return taskList.addTask(new Deadline(name, deadline));
    }

    /**
     * Creates a duke.task.Event instance and adds it to duke.ToDoList
     *
     * @throws Exception
     */
    private String addEvent() throws Exception {
        if (!fullCommand.matches("event \\S.*")) {
            throw new Exception("The description of an event cannot be empty.");
        } else if (!fullCommand.contains("/at")) {
            throw new Exception("The description is missing a time.");
        }

        String details = fullCommand.substring(fullCommand.indexOf(" ") + 1);
        String name = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        return taskList.addTask(new Event(name, time));
    }

    /**
     * Finds tasks whose name matches search
     * @throws Exception
     */
    private String findTask() throws Exception {
        if (isFindPriority()) {
            String priority = fullCommand.substring(fullCommand.indexOf("/p") + 2);
            return findTaskPriority(priority);
        } else {
            String searchString = fullCommand.substring(fullCommand.indexOf(" ") + 1);
            return taskList.findTaskName(searchString);
        }
    }

    /**
     * Finds Task Priority using taskList
     * @param priorityString
     * @return response from duke after trying to find task with specified priority
     * @throws Exception
     */
    private String findTaskPriority (String priorityString) throws Exception{
        if (!priorityString.matches("[lmh-]")) {
            throw new Exception("Please specify a valid priority:\nLOW: l\nMEDIUM: m\nHIGH: h\nNONE: -");
        }
        return taskList.findTaskPriority(priorityString);
    }

    /**
     * Sets task priority from user input
     * @return Response after trying to set priority of a task
     * @throws Exception
     */
    private String setTaskPriority() throws Exception{
        if(!fullCommand.matches( "priority [0-9]+.+")) {
            throw new Exception("Please specify a valid index");
        } else if (!fullCommand.matches("priority [0-9]+ [lmh-]")){
            throw new Exception("Please specify a valid priority:\nLOW: l\nMEDIUM: m\nHIGH: h\nNONE: -");
        }
        String[] splitComm = fullCommand.split(" ");
        assert splitComm.length == 3 : "priority is missing fields";
        int index = Integer.parseInt(splitComm[1]) - 1;
        Task.PriorityLevel priorityLevel = Task.commandToPriorityLevel(splitComm[2]);
        return taskList.setTaskPriority(index, priorityLevel);
    }

    private boolean isFindPriority() throws Exception {
        return fullCommand.startsWith("find /p");
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
