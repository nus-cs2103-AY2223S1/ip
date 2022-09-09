package raiden.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import raiden.Raiden;

/**
 * @@author HowSuen-reused
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
 *
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

    private Raiden raiden;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Raiden.png"));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = "Hello, I'm Raiden!\n" + "What can I do for you?\n";
        this.dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetingMessage, this.dukeImage));
    }

    /**
     * Sets the raiden of this MainWindow as the Raiden object given.
     *
     * @param d The given Raiden object.
     */
    public void setDuke(Raiden d) {
        this.raiden = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Raiden's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.raiden.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
