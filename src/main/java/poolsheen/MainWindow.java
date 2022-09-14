package poolsheen;

import java.io.IOException;

import javafx.fxml.FXML;
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

    private Poolsheen poolsheen;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Pusheen.png"));


    /**
     * Runs whenever the GUI first initializes.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.getWelcome(), dukeImage)
        );
    }

    public void setPoolsheen(Poolsheen p) throws IOException {
        if (Poolsheen.getExited()) {
            throw new IOException("Error with loading");
        }
        poolsheen = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert dukeImage != null : "dukeImage path should not be null";
        assert userImage != null : "userImage path should not be null";
        if (Poolsheen.getExited()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(Ui.getGoodbye(), dukeImage)
            );
            userInput.clear();
        } else {
            String input = userInput.getText();
            String response = poolsheen.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }
}
