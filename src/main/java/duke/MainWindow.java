package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.PNG"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        printHelloMsg();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        duke.takeCommand(input);
        userInput.clear();
    }

    /**
     * Prints the list of tasks in the chat
     */
    public void printList(TaskList tasks) {
        String tasksStr = "Here you tasks:\n";
        for (int j = 0; j < tasks.getSize(); j++) {
            tasksStr = tasksStr.concat((j + 1) + ". " + tasks.getTask(j).toString() + "\n");
        }
        tasksStr.concat("Stop asking me to do stuff");
        Label dukeText = new Label(tasksStr);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a hello message
     */
    public void printHelloMsg() {
        Label dukeText = new Label("Duke what u want");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
        System.out.println();
    }

    /**
     * Prints a message after a task has been successfully added
     *
     * @param task of type Task
     * @param size of type int
     */
    public void printAddSuccessfulMsg(Task task, int size) {
        String msg = "Okok.\n"
                + task.toString()
                + " added. \nNow got " + size + " tasks already, stop adding please";
        Label dukeText = new Label(msg);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message after user enters a find command
     *
     * @param tasks The input to be received
     */
    public void printFindTasks(ArrayList<Task> tasks) {
        String tasksStr = "Here the matching tasks:\n";
        for (int i = 0; i < tasks.size(); i ++) {
            tasksStr = tasksStr.concat((i + 1) + ". " + tasks.get(i) + "\n");
        }
        tasksStr.concat("stop asking me to do stuff");
        Label dukeText = new Label(tasksStr);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message after a task has been successfully deleted
     *
     * @param task of type Task
     * @param size of type int
     */
    public void printDeleteSuccessfulMsg(Task task, int size) {
        Label dukeText = new Label("Kk. Removed:\n" + task.toString() +
                "\nNow got " + size + " tasks in the list. Stop ordering me around");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message after a task has been successfully updated
     *
     * @param task of type Task
     */
    public void printUpdateSuccessfulMsg(Task task) {
        Label dukeText = new Label("Kk. Updated:\n" + task.toString() +
                "\nStop ordering me around");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message after a task has been successfully marked
     *
     * @param task The input to be received
     */
    public void printMarkTaskSuccessfulMsg(Task task) {
        Label dukeText = new Label("Kk marked (" + task + ") already");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message after a task has been successfully unmarked
     *
     * @param task The input to be received
     */
    public void printUnMarkTaskSuccessfulMsg(Task task) {
        Label dukeText = new Label("Kk unmarked (" + task + ") already");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message if the user input unknown command
     */
    public void printDontUnderstandMsg() {
        Label dukeText = new Label("What you talking, type also dont want type properly");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints a message if the user inputs a command such as deadline without a second parameter
     *
     * @param typeStr The input to be received
     */
    public void printDescriptionCantBeEmptyMsg(String typeStr) {
        Label dukeText = new Label("Sorry this not your brain, " + typeStr + " cannot be empty");
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }

    /**
     * Prints an error message
     *
     * @param e The input to be received
     */
    public void printErrorMessage(String e) {
        Label dukeText = new Label("Error: " + e);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(dukeImage).getImage())
        );
    }
}