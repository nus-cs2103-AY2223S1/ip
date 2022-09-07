package duke.gui;

import duke.core.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

// @@author Piyotato-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications.

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String WELCOME_MESSAGE = "Hi! I am Duke! Let's have a chat!";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
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
        String response = duke.handleUserInput(input);
        dialogContainer.getChildren().addAll(
                UserDialogBox.of(input, userImage),
                DukeDialogBox.of(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Shows the welcome message in the main window.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DukeDialogBox.of(WELCOME_MESSAGE, dukeImage)
        );
    }

    /**
     * Places a dialog box into the window.
     *
     * @param dialogBox DialogBox to place.
     */
    public void createDialogBox(DialogBox dialogBox) {
        dialogContainer.getChildren().addAll(dialogBox);
    }
}

// @@author
