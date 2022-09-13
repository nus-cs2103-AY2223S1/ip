package duke.display;

import duke.Duke;
import duke.command.ByeCommand;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/SheldonUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/SheldonDuke.png"));

    /**
     * Initializes the UI and shows duke's welcome Text.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Duke.welcomeText(), dukeImage, false));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Initialises duke with the param duke
     * @param d duke
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
        Pair<String, Boolean> response = duke.getResponse(input);
        String message = response.getKey();
        Boolean hasError = response.getValue();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(message, dukeImage, hasError)
        );
        if (message == ByeCommand.DUKE_END) {
            Platform.exit();
        }
        userInput.clear();
    }
}
