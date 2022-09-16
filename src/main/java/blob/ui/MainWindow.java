package blob.ui;
import blob.Blob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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

    private Blob blob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image blobImage = new Image(this.getClass().getResourceAsStream("/images/blob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont((Font.font("Georgia", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14)));
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String[] responses = blob.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userImage, input),
            DialogBox.getBlobDialog(blobImage, responses)
        );
        userInput.clear();
    }
}
