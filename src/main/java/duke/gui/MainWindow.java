package duke.gui;

import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Penguin.png"));
    private Image molediverImage = new Image(this.getClass().getResourceAsStream("/images/Molediver.png"));

    private static final String GREETING_MESSAGE = "Hi, I'm molediver. What can I do for you?";

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(GREETING_MESSAGE,molediverImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        checkByeMessage(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, molediverImage)
        );
        userInput.clear();
    }

    private void checkByeMessage(String response) {
        String byeMessage = "Bye. Hope to see you again soon!";
        if (response == byeMessage) {
            TimerTask exitTask = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            new Timer().schedule(exitTask, 750);
        }
    }

}
