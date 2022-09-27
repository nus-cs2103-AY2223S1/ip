package duke;

import java.util.Objects;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.jpeg"));

    /**
     * Initialize the Main Window
     */
    @FXML
    public void initialize() {
        String greetings = new Ui().greet();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings, dukeImage));
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
        String[] response = duke.getResponse(input);
        String returnMessage = response[0];
        String toExit = response[1];

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(returnMessage, dukeImage)
        );

        userInput.clear();

        if (Objects.equals(toExit, "1")) {
            PauseTransition termination = new PauseTransition(Duration.seconds(1));
            termination.setOnFinished(event -> Platform.exit());
            termination.play();
        }
    }
}

