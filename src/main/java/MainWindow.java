import java.util.Timer;
import java.util.TimerTask;

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

    private Luna luna;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image lunaImage = new Image(this.getClass().getResourceAsStream("/images/luna.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void welcome() {
        String welcome = luna.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getLunaDialog(welcome, lunaImage)
        );
        userInput.clear();
    }

    public void setLuna(Luna l) {
        assert l != null;
        luna = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLunaDialog(response, lunaImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 800L);
        }
    }
}
