package duke.gui;

import duke.services.Duke;
import duke.services.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() throws IOException {
        //Scroll down to the end every time dialogContainer's height changes
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        Duke.activate();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.getReply(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (!Duke.isActive()) {
            userInput.setDisable(true);
            sendButton.setDisable(true);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 2000);
        }
    }
}
