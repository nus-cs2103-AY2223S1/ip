package duke.ui;

import duke.Duke;
import duke.DukeException;
import duke.Main;
import duke.command.Command;
import duke.command.ExitCommand;
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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Initialize the main window.
     */
    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        String welcomeText = "Hello! I'm Duke.\nWhat can I do for you?";
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeText, this.dukeImage));
    }

    /**
     * Sets the given Duke as this Duke.
     *
     * @param duke The Duke to set.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    // @@author jorrdansoh-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String userText = this.userInput.getText();
            Command command = this.duke.parseText(userText);
            String dukeText = command.execute();
            this.duke.saveTasks();

            this.dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, this.userImage),
                    DialogBox.getDukeDialog(dukeText, this.dukeImage)
            );

            if (command instanceof ExitCommand) {
                Main.close();
            }
        } catch (DukeException e) {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), this.dukeImage));
        } finally {
            this.userInput.clear();
        }
    }
    // @@author
}
