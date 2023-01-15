package blink;

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

    private Blink blink = new Blink();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserAvatar.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/BlinkAvatar.png"));

    /**
     * Begins the gui
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMsg = "Hello Blink here. \n"
                + "What can I do for you today ^-^";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMsg, dukeImage)
        );
    }

    public void setBlink(Blink blink) {
        this.blink = blink;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = blink.getResponse(input);
        assert response != null;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
