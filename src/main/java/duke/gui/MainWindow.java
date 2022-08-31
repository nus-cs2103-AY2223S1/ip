package duke.gui;

import duke.Duke;
import duke.exception.DukeException;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke() {
        try {
            duke = new Duke();
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(e.getMessage(), dukeImage));
        } finally {
            dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.greet(), dukeImage));
        }
    }

    public boolean isStillRunning() {
        return !userInput.isDisabled();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = duke.reply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getResponseMessage(), dukeImage)
        );
        userInput.clear();

        if (response.getResponseType() == ResponseType.QUIT) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
        }
    }
}
