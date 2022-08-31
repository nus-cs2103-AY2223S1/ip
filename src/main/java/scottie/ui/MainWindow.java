package scottie.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import scottie.Scottie;

/**
 * Controller for MainWindow. Provides the Layout for the other controls.
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

    private Scottie scottie;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image scottieImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/scottie.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setScottie(Scottie scottie) {
        this.scottie = scottie;
    }

    /**
     * Creates two dialog boxes, one echoing the user's input and the other
     * containing Scottie's reply, and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        String response = "Temporary fixed response";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getScottieDialog(response, scottieImage)
        );
        userInput.clear();
    }
}
