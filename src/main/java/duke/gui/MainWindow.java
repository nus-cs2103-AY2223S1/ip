package duke.gui;

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
 * Controller for gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** <Code>String</Code> to be shown on initialization. */
    private static final String GREETING =
            "Duke at your service. How may I help you?\n";

    /** <Code>String</Code> of all available commands and their format. */
    private static final String AVAILABLE_COMMANDS =
            "Available commands:\n"
                    + "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n"
                    + "   event      [TASK DESCRIPTION] /at [VENUE]\n"
                    + "   todo       [TASK DESCRIPTION]\n"
                    + "   delete     [TASK NUMBER]\n"
                    + "   mark       [TASK NUMBER]\n"
                    + "   unmark   [TASK NUMBER]\n"
                    + "   list\n"
                    + "   find         [WORD TO SEARCH FOR]\n"
                    + "   bye\n";

    /** Image to represent user. */
    private final Image userImage =
            new Image(this.getClass().getResourceAsStream(
                    "/images/DaUser.png"));

    /** Image to represent Duke. */
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream(
                    "/images/DaDuke.png"));

    /** Instance of <Code>Duke</Code> object to chat with. */
    private Duke duke;

    /** Container for chat content to scroll. */
    @FXML
    private ScrollPane scrollPane;

    /** Container for dialog chat boxes between Duke and user. */
    @FXML
    private VBox dialogContainer;

    /** Component to show user's text input. */
    @FXML
    private TextField userInput;

    /** Button for user to send text to Duke. */
    @FXML
    private Button sendButton;

    /**
     * Initializes <Code>MainWindow</Code> and greets user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(GREETING + AVAILABLE_COMMANDS,
                        dukeImage)
        );
    }

    /**
     * Sets the <Code>Duke</Code> instance which user is talking to
     * and whom users will get responses from.
     * @param d Duke object to get responses from.
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
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (!duke.checkIfRunning()) {
            // If duke is no longer running, close application after
            // a 1.5-second pause.
            PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
            delay.setOnFinished( event -> Platform.exit());
            delay.play();
        }
    }
}
