import duke.Shiba;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Main Window for the application GUI
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

    private Shiba duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Neko.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Shiba.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Shiba d) {
        duke = d;
    }

    /**
     * Greets the user with a greeting message.
     */
    public void greetUser() {
        String line1 = "Hello! I'm Shiba \uD83D\uDC15\n";
        String line2 = "The task management dog you can trust!\n";
        String line3 = "What can I do for you today?";
        String greetMessage = line1 + line2 + line3;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greetMessage, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (duke.isClosed()) {
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }
}
