package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /**
     * Scrollable container;
     */
    @FXML
    private ScrollPane scrollPane;
    /**
     * Container for the input and responses.
     */
    @FXML
    private VBox dialogContainer;
    /**
     * The user input.
     */
    @FXML
    private TextField userInput;
    /**
     * The send button.
     */
    @FXML
    private Button sendButton;

    /**
     * An instance of duke.
     */
    private Duke duke;

    /**
     * The user image.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));

    /**
     * The server image.
     */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Sets up pre requisites for duke to run.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke instance.
     *
     * @param duke1 The instance of duke to use
     */
    public void setDuke(Duke duke1) {
        duke = duke1;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's response
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
