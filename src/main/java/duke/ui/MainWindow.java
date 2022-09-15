package duke.ui;

// @@author j-lum-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with some modifications
import duke.Duke;
import duke.commands.CommandResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author sikai00
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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/KarenBot.jpg"));
    // @authors
    /**
     * Initializes the MainWindow.
     * This is used by FXMLLoader.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Greet user upon GUI initialize
        String greetingMsg = "What do you want? Do you know who I am?\n(psst! type in help for help with commands!)";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetingMsg, dukeImage)
        );
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
        CommandResult response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response.getMessage(), dukeImage)
        );
        if (response.hasNode()) {
            dialogContainer.getChildren().add(response.getNode());
        }
        userInput.clear();
    }
}
