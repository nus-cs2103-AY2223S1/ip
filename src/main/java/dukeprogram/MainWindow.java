package dukeprogram;

import java.util.Iterator;
import java.util.LinkedList;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    private boolean hasStoppedResponse = true;
    private final LinkedList<DukeResponse> queuedResponses = new LinkedList<>();

    /**
     * Initialises the main window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        duke = new Duke(this);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        System.out.println("Handling");
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.ofUser(input, duke.getUser()));

        duke.parseInput(input);

        userInput.clear();
    }

    private void consumeResponse(Iterator<DukeResponse> responseIterator) {
        if (responseIterator.hasNext()) {
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                dialogContainer.getChildren().add(responseIterator.next().createDialogBox());
                consumeResponse(responseIterator);
            });
            pause.play();
        }
    }

    private void consumeResponse() {
        hasStoppedResponse = false;
        if (!queuedResponses.isEmpty()) {
            DukeResponse nextResponse = queuedResponses.poll();
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                dialogContainer.getChildren().add(nextResponse.createDialogBox());
                consumeResponse();
            });
            pause.play();
        } else {
            hasStoppedResponse = true;
        }
    }

    /**
     * Sends a dialog from Duke to the user
     * @param response the response to show to the user
     */
    public void sendDukeDialog(DukeResponse response) {
        queuedResponses.addLast(response);

        if (hasStoppedResponse) {
            consumeResponse();
        }
    }
}
