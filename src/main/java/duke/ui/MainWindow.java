package duke.ui;

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

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    @FXML
    private VBox dialogContainer;

    @FXML
    private ScrollPane scrollPane;

    private Duke duke;

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/owl.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/penguin.png"));

    /**
     * Initialises the @FXML fields
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox greeting = DialogBox.getDukeDialog(dukeImage, "Hello! I'm Duke\nWhat can I do for you ^_^");
        dialogContainer.getChildren().add(greeting);
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Handles user's input after clicking the send button or enter
     */
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox user = DialogBox.getUserDialog(userImage, input);
        DialogBox duke = DialogBox.getDukeDialog(dukeImage, response);
        dialogContainer.getChildren().addAll(user, duke);
        userInput.clear();
    }
}
