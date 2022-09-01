package duke;

import javafx.application.Application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Contains methods to print information to user's UI.
 *
 * @author Siau Wee
 */
public class Ui extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Scene scene;

    private TaskList taskList;
    private final Image userImage = new Image(this.getClass().getClassLoader()
            .getResource("images/nerd.png").toString());
    private final Image dukeImage = new Image(this.getClass().getClassLoader()
            .getResource("images/gorillaW.png").toString());

    public Ui() {
        this.taskList = new TaskList();
    }

    /**
     * Prints a confirmation that the given task has been added to array.
     * 
     * @param task The task that has been added.
     */
    public void taskAddedMessage(Task task) {
        String response = "Task added: " + task;
        response += "\n" + this.taskListSizeMessage();
        this.dukeRespond(response);
    }

    /**
     * Prints a confirmation that the given task has been deleted from array.
     * 
     * @param task The task that has been deleted.
     */
    public void taskDeletedMessage(Task task) {
        String response = "Task removed: " + task;
        response += "\n" + this.taskListSizeMessage();
        this.dukeRespond(response);
    }

    /**
     * Prints a confirmation that the given task has been marked done.
     * 
     * @param task The task that has been marked done.
     */
    public void taskMarkedMessage(Task task) {
        String response = "Task marked as done: \n" + task;
        this.dukeRespond(response);
    }

    /**
     * Prints a confirmation that the given task has been marked undone.
     * 
     * @param task The task that has been marked undone.
     */
    public void taskUnmarkedMessage(Task task) {
        String response = "Task marked as not done: \n" + task;
        this.dukeRespond(response);
    }

    /**
     * Prints all task to output.
     */
    public void listTask() {
        String response = "Listing your current tasks: \n";
        try {
            for (int i = 0; i < this.taskList.getSize(); ++i) {
                response += (i + 1) + ". " + this.taskList.getTask(i) + "\n";
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        this.dukeRespond(response);
    }

    /**
     * Prints all the tasks that match the user's search term.
     *
     * @param foundTasks The tasks that match the user's input search term.
     */
    public void taskFoundMessage(ArrayList<String> foundTasks) {
        String response = "";
        if (foundTasks.size() == 0) {
            this.dukeRespond("No tasks matching your search term found.");
        } else {
            response += "Tasks found: \n";
            for (int i = 0; i < foundTasks.size(); ++i) {
                response += foundTasks.get(i) + "\n";
            }
            this.dukeRespond(response);
        }
    }

    /**
     * Creates and displays a dialog in GUI before exiting program.
     */
    public void byeMessage() {
        this.dukeRespond("Goodbye, don't get it twisted!");
    }

    /**
     * Creates and displays a response dialog in the GUI with response as the message
     * to be displayed
     * @param response The message to be displayed in a dialog box on the GUI
     */
    public void dukeRespond(String response) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }

    /**
     * Prints a message describing the size of the current task array.
     * 
     * @return String describing the size of task array.
     */
    public String taskListSizeMessage() {
        int currSize = this.taskList.getSize();
        if (currSize == 1) {
            return "There is " + currSize + " task in your list.";
        } else {
            return "There are " + currSize + " tasks in your list.";
        }
    }

    /**
     * Prints a greeting as well as initialise assets of GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Hi, my name is Duke and it's power hour!\n" +
                "Commands are CASE SENSITIVE: enter all commands in lowercase.\n";
        String commandGuide = "Please input all dates and times in the following format:\n" +
                "dd-MM-yyyy-HH-mm (e.g. 23-04-2000-23-04)";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greeting, dukeImage),
                DialogBox.getDukeDialog(commandGuide, dukeImage)
        );
    }

    /**
     * Takes in user input, parses it and runs the relevant commands if the input matches
     * required command format.
     */
    @FXML
    private void handleUserInput() {
        String command = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(command, userImage));
        String[] commandArgs = command.split(" ", 2);
        String response = "lol";
        try {
            Command commandToExecute = Parser.parseCommand(commandArgs);
            this.executeCommand(commandToExecute);
        } catch (DukeException e) {
            dukeRespond(e.getMessage());
        }
        userInput.clear();
    }

    /**
     * Calls the relevant method based on the Command passed as argument.
     *
     * @param command The Command to be executed.
     * @throws DukeException If there is an invalid command keyword or arguments.
     */
    public void executeCommand(Command command) throws DukeException {
        switch (command.getKeyword()) {
            case BYE:
                TimerTask timerTask = new TimerTask() {
                    public void run () {
                        System.exit(0);
                    }
                };
                new Timer().schedule(timerTask, 1500);
                this.byeMessage();
                break;
            case LIST:
                this.listTask();
                break;
            case MARK:
                Task markedTask = this.taskList.markTask(Integer.parseInt(command.getCommandArgs()[1]));
                this.taskMarkedMessage(markedTask);
                break;
            case UNMARK:
                Task unmarkedTask = this.taskList.unmarkTask(Integer.parseInt(command.getCommandArgs()[1]));
                this.taskUnmarkedMessage(unmarkedTask);
                break;
            case DELETE:
                Task removedTask = this.taskList.deleteTask(Integer.parseInt(command.getCommandArgs()[1]));
                this.taskDeletedMessage(removedTask);
                break;
            case TODO:
                Todo todo = new Todo(command.getCommandArgs()[1]);
                this.taskList.addTask(todo);
                this.taskAddedMessage(todo);
                break;
            case DEADLINE:
                Deadline deadline = new Deadline(command.getCommandArgs()[0],
                        Parser.parseDateTime(command.getCommandArgs()[1]));
                this.taskList.addTask(deadline);
                this.taskAddedMessage(deadline);
                break;
            case EVENT:
                Event event = new Event(command.getCommandArgs()[0],
                        Parser.parseDateTime(command.getCommandArgs()[1]));
                this.taskList.addTask(event);
                this.taskAddedMessage(event);
                break;
            case FIND:
                String chars = command.getCommandArgs()[1];
                ArrayList<String> foundTasks = this.taskList.findTask(chars);
                this.taskFoundMessage(foundTasks);
            default:
                return;
        }
    }

    /**
     * Method to launch the GUI application with the given stage.
     * @param stage Base stage to load GUI with
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass()
                    .getClassLoader().getResource("view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
