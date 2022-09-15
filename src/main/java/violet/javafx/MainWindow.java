package violet.javafx;

import javafx.scene.layout.*;
import violet.Violet;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.Duration;

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

    private Violet violet;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bokuto.jpg"));
    private Image violetImage = new Image(this.getClass().getResourceAsStream("/images/violet.jpg"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/background.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(new Background(new BackgroundImage(this.backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
    }

    public void setViolet(Violet v) {
        violet = v;
    }

    @FXML
    public void greeting() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(violet.greeting(), violetImage),
                DialogBox.getDukeDialog(violet.load(), violetImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Violet's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = violet.getResponse(input);
        dialogContainer.getChildren().add(UserDialogBox.getUserDialog(input, userImage));
        if (input.equals("bye")) {
            violet.save();
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
            delay.setOnFinished(scene -> {
                Platform.exit();
                System.exit(0);
            });
            delay.play();
            userInput.setDisable(true);
            sendButton.setDisable(true);
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, violetImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, violetImage));
        }
        userInput.clear();
    }
}
