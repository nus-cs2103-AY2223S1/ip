package duke;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gru.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/minion.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Duke object in the field instance.
     *
     * @param d The Duke object to be set.
     */
    public void setDuke(Duke d) {
        duke = d;
        //duke.sendWelcomeMessage(dialogContainer, dukeImage);
    }

    /**
     * Displays the very first message when user opens the chatbot.
     */
    public void sendWelcomeMessage() {
        duke.sendWelcomeMessage(dialogContainer, dukeImage);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        duke.handleUserInput(input, dialogContainer, userImage, dukeImage);
        userInput.clear();
    }
}
