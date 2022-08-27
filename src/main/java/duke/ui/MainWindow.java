package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window of the Duke application.
 * This provides the layout for the other controls.
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

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.jpeg"))
    );
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Detect if the user clicks up and down arrow keys for command history navigation.
        userInput.setOnKeyPressed(event -> {
            String commandToBeChangedTo = null;
            KeyCode keyCode = event.getCode();
            if (keyCode.equals(KeyCode.UP) || keyCode.equals(KeyCode.KP_UP)) {
                commandToBeChangedTo = duke.getPreviousCommand();
            } else if (keyCode.equals(KeyCode.DOWN) || keyCode.equals(KeyCode.KP_DOWN)) {
                commandToBeChangedTo = duke.getNextCommand();
            }
            if (commandToBeChangedTo != null) {
                userInput.setText(commandToBeChangedTo);
                // Change caret / cursor position.
                userInput.positionCaret(commandToBeChangedTo.length());
            }
        });
    }

    /**
     * Sets the Duke instance for the main window.
     *
     * @param duke Duke instance associated with the main window.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one for the user and one for Duke application
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        // Clear user input;
        userInput.clear();
    }
}
