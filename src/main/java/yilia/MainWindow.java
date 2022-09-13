package yilia;

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

    private Yilia yilia;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image yiliaImage = new Image(this.getClass().getResourceAsStream("/images/yilia.jpg"));
    /**
     * Initializes the window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYilia(Yilia y) {
        yilia = y;
        dialogContainer.getChildren().addAll(
                DialogBox.getYiliaDialog("Hello! I'm Yilia. ( ° ∀ ° )ﾉﾞ \nWhat can I do for you?", yiliaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yilia's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yilia.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYiliaDialog(response, yiliaImage)
        );
        userInput.clear();
    }
}
