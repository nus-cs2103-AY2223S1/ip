package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialogue(input, userImage)
        );

        if (!Duke.getIsRunning()) {
            stage.close();
        }

        Duke.giveInput(input);
        getResponse();

        userInput.clear();
    }

    public void getResponse() {
        dialogueContainer.getChildren().add(
                DialogueBox.getDukeDialogue(Duke.getResponse(), dukeImage)
        );
        Duke.clearResponse();
    }
}
