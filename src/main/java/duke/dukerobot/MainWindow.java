package duke.dukerobot;

import duke.dukeexception.DukeException;

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
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /**
     * Initialize the scrollpane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DialogBox d = DialogBox.getDukeDialog("Hi, traveller.\nWhat's your plan today?", dukeImage);
        dialogContainer.getChildren().addAll(d);
    }
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If exception is caught then creates special dialog box for exception message.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        } catch (DukeException e) {
            String response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getExceptionDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}