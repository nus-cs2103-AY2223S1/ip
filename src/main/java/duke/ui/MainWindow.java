package duke.ui;

import java.util.Objects;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * @author Emily Ong Hui Qi
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ImageView dukePicture;
    @FXML
    private Button sendButton;
    @FXML
    private Label appLabel;

    private Duke duke;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png"))
    );
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Duke.png"))
    );

    protected void setDuke(Duke duke) {
        this.duke = duke;
    }

    private void disableSendButtonIfEmptyUserInput() {
        this.sendButton.setDisable(this.userInput.getText().length() == 0);
    }

    /**
     * Initializes the main window application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dukePicture.setImage(this.dukeImage);
        this.appLabel.setText(Duke.NAME);
        this.disableSendButtonIfEmptyUserInput();

        // Greet the user
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(Duke.MESSAGE_GREETING, this.dukeImage));
    }

    @FXML
    private void handleUserInput() {
        this.disableSendButtonIfEmptyUserInput();
    }

    @FXML
    private void handleCommand() {
        String input = this.userInput.getText();
        String response = this.duke.handleCommand(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(response, this.dukeImage)
        );
        this.userInput.clear();
        this.userInput.requestFocus();
        this.disableSendButtonIfEmptyUserInput();
    }
}
