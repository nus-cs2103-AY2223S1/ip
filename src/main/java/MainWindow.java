
import duke.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        boot(duke);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        if (input.equals("boot")) {
            userInput.clear();
            addDialogBox(DialogBox.getUserDialog("boot", userImage));
            boot();
            return;
        }
        String response = duke.getResponse(input);
        userInput.clear();
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        addDialogBox(userDialog);
        addDialogBox(dukeDialog);
    }

    @FXML
    private void addDialogBox(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    @FXML
    private void boot(Duke... dukes) {
        if (dukes.length == 0) {
            this.duke = new Duke();
        } else if (dukes[0] == null) {
            this.duke = new Duke();
        } else {
            this.duke = dukes[0];
        }
        addDialogBox(DialogBox.getDukeDialog(duke.getResponse("greet"), dukeImage));
    }
}
