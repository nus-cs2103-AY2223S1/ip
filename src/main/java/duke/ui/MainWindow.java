package duke.ui;

import duke.main.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    // @@author @hauchongtang
    // GREETINGS and INSTRUCTIONS adapted
    private static final String GREETING = "Your highness. I am Knave of Hearts and I am here to serve you.";
    private static final String INSTRUCTIONS = "To see all tasks type list\n" + "To add a todo, type todo <task>\n"
            + "To add a deadline, type deadline <task> /by <yyyy-mm-dd>\n"
            + "To add an event, type event <task> /at <yyyy-mm-dd>\n"
            + "To mark a task as done, type mark <task number>\n"
            + "To mark a task as undone, type unmark <task number>\n" + "To delete a task, type delete <task number>\n"
            + "To search for task descriptions, type find <query>\n"
            + "To tag a task, type tag <task number> /t <tag> \n"
            + "To untag a task, type untag <task number> /t <tag> \n";
    //@@author

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/redqueen.jfif"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/knaveofhearts.jfif"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Duke duke;

    /**
     * Initialise empty MainWindow
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setStyle("-fx-background-color: transparent");
        dialogContainer.setStyle("-fx-background-color: transparent");
        dialogContainer.getChildren()
                .addAll(DialogBox.getDukeDialog(GREETING, dukeImage), DialogBox.getDukeDialog(INSTRUCTIONS, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String fullCommand = userInput.getText();

        if (duke.getExit()) {
            Platform.exit();
            return;
        }

        String response = duke.getResponse(fullCommand);

        dialogContainer.getChildren()
                .addAll(DialogBox.getUserDialog(fullCommand, userImage), DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

    }
}
