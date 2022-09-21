package duke.main;

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

import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
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

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Duke.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    @FXML
    public void greeting() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(duke.greeting(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        if (input.equals("bye")) {
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