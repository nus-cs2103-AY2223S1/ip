package ted.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ted.DialogBox;

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

    private UiController ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image tedImage = new Image(this.getClass().getResourceAsStream("/images/Ted.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setUi(UiController ui) {
        this.ui = ui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        ui.handleInput(input);
        userInput.clear();
    }

    /**
     * Add a dialog box to output a message
     * @param message
     */
    public void output(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(message, tedImage)
        );
    }
}
