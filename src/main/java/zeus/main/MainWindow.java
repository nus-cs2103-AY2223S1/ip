package zeus.main;

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

    private Zeus zeus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/greek_citizen_icon.png"));
    private Image zeusImage = new Image(this.getClass().getResourceAsStream("/images/zeus_icon.png"));

    /**
     * Initialises the chat.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Initialise chatbot for MainWindow.
     *
     * @param z Chatbot to be used for MainWindow
     */
    public void setZeus(Zeus z) {
        zeus = z;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zeus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zeus.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZeusDialog(response, zeusImage)
        );
        userInput.clear();
    }

    @FXML
    private void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getZeusDialog("Greetings. What can I do for you?", zeusImage)
        );
    }
}
