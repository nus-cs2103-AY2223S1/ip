package duke.javafx;

import duke.Duke;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    /** The Duke object to be run. */
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Stores the input d.
     *
     * @param d The duke object to be stored.
     */
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
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        if (response.equals("bye")) {
            duke.saveTasks();
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished(event -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
            userInput.setDisable(true);
            sendButton.setDisable(true);
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.exitMessage(), dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }
        userInput.clear();
    }

    @FXML
    public void init() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.startMessage(), dukeImage));
    }
}
