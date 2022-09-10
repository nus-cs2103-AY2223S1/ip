package mort.ui;

import javafx.animation.PauseTransition;
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
import mort.Mort;

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

    private Mort mort;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image mortImage = new Image(this.getClass().getResourceAsStream("/images/Mort.png"));
    private BackgroundImage background = new BackgroundImage(
            new Image(this.getClass().getResourceAsStream("/images/parchmentbg.jpeg")),
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    /**
     * Initializes properties of userInput, scrollPane, dialogContainer and sendButton.
     */
    @FXML
    public void initialize() {
        userInput.setStyle("-fx-control-inner-background: #DCD7C9; -fx-background-radius: 5px;");
        scrollPane.setBackground(new Background(background));
        dialogContainer.setBackground(new Background(background));
        sendButton.setStyle("-fx-background-color: #553939; -fx-background-radius: 5px; -fx-text-fill: #ffffff");
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setMort(Mort m) {
        mort = m;
        showWelcomeMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mort.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMortDialog(response, mortImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            //reused syinyichen
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                System.exit(0);
            });
            pause.play();
        }
    }

    private void showWelcomeMessage() {
        dialogContainer.getChildren().addAll(DialogBox.getMortDialog(mort.welcome(), mortImage));
    }
}
