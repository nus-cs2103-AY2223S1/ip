package bobby.gui;
import bobby.Bobby;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * Main GUI function
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

    private Bobby duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/bobby.png"));
    private Image lobbyImage = new Image(this.getClass().getResourceAsStream("/images/lobby2.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello fellow crewmate \nWhat can i do for u today?", dukeImage)
        );
        BackgroundImage bImg = new BackgroundImage(lobbyImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        dialogContainer.setBackground(bGround);


    }

    public void setLobbyBackground() {


    }

    public void setDuke(Bobby d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        DialogBox responseDialog = DialogBox.getDukeDialog(response, dukeImage);
        responseDialog.setUserBackground();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            handleBye();
        }
    }

    private void handleBye() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }


}
