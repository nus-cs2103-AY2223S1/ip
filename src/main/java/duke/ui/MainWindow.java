package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

/**
 * Controller for MainWindow that provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Ensures that the ScrollPane shows the most recent message on the screen.
     */
    @FXML
    public void initialize() {
        //@@author ruiqi7-reused
        //Reused from https://github.com/nus-cs2103-AY2223S1/ip/pull/77/commits/0f4f0d2a7870d75d0022dddacffb8196c00ab778
        //with minor modifications
        scrollPane.setOnScroll(event -> scrollPane.setVvalue(scrollPane.getVvalue() - event.getDeltaY()));
        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Initialises Duke. Shows loading errors if any and the greeting message to the user.
     */
    public void setDuke(Duke d) {
        duke = d;
        String loadingError = duke.showLoadingError();
        if (!loadingError.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(loadingError, dukeImage, true));
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(duke.greetUser(), dukeImage, false));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            Pair<String, Boolean> response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response.getKey(), dukeImage, response.getValue())
            );
            userInput.clear();
        }
    }
}
