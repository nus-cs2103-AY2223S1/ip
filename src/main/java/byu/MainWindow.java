package byu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The controller for MainWindow. Provides the layout for the other controls.
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

    private Byu byu;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.gif"));
    private final Image byuImage = new Image(this.getClass().getResourceAsStream("/images/panda.png"));
    private final DialogBox welcomeDialogBox = DialogBox.getByuDialog(Ui.LOGO + Ui.WELCOME_MESSAGE, byuImage);

    /**
     * Initializes the main window, and displays a welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(welcomeDialogBox);
    }

    public void setByu(Byu b) {
        byu = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Byu's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = byu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getByuDialog(response, byuImage)
        );
        userInput.clear();
    }
}
