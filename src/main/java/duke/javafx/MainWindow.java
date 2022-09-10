package duke.javafx;

import duke.Duke;
import duke.javafx.DialogBox;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public void greeting() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.greeting(), dukeImage),
                DialogBox.getDukeDialog(duke.load(), dukeImage));
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
        if (input.equals("bye")) {
            duke.save();
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(scene -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
            userInput.setDisable(true);
            sendButton.setDisable(true);
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }
        userInput.clear();
    }
}
