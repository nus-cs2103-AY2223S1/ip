package Cinnamon.GUI;
import Cinnamon.Cinnamon;
import Cinnamon.Exception.DukeException;
import javafx.application.Platform;
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

    private Cinnamon cinnamon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image cinnamonImage = new Image(this.getClass().getResourceAsStream("/images/cinnamon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCinnamon(Cinnamon d) {
        cinnamon = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(cinnamon.displayGreet(), cinnamonImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        String input = userInput.getText();
        String response = cinnamon.getResponse(input);
        if (input.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(cinnamon.displayBye(), cinnamonImage)
            );
           Platform.exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, cinnamonImage)
        );
        userInput.clear();
    }
}
