package dukeprogram;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import javafx.animation.PauseTransition;
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

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image dukeImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Initialises the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        for (String response : Duke.start()) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] responses = Duke.getResponses(input);
        System.out.println(Arrays.toString(responses));

        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));

        Iterator<String> responseStream = Arrays.stream(responses).iterator();
        consumeResponse(responseStream);

        userInput.clear();
    }

    private void consumeResponse(Iterator<String> responseIterator) {
        if (responseIterator.hasNext()) {
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(responseIterator.next(), dukeImage));
                consumeResponse(responseIterator);
            });
            pause.play();
        }
    }
}
