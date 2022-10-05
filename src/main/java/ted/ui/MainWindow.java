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

    private UiController uiController;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image tedImage = new Image(this.getClass().getResourceAsStream("/images/Ted.png"));

    @FXML
    public void initialize() {
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set ui controller
     *
     * @param uiController
     */
    public void setUiController(UiController uiController) {
        assert uiController != null : "ui should not be null";
        this.uiController = uiController;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Ted's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        uiController.handleInput(input);
        userInput.clear();
    }

    /**
     * Add a dialog box to output a message
     *
     * @param message
     */
    public void output(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getTedDialog(message, tedImage)
        );
    }

    public void outputError(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getErrorDialog(message, tedImage)
        );
    }
}
