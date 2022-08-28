package duke.gui.controller;

import duke.Duke;
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
    /** Display image of the client. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/angrydoge.png"));
    /** Display image of the duke chat-bot. */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/derpydoge.png"));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /** Duke object associated with the JavaFx application. */
    private Duke duke;

    /** Scrolls the ScrollPane automatically to the bottom. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Initialises the duke object, then display the greet message. */
    public void setDuke(Duke d) {
        duke = d;
        DialogBox greetingDialog = DialogBox.getDukeDialog(duke.getGreetingMessage(), dukeImage);
        dialogContainer.getChildren().addAll(greetingDialog);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
