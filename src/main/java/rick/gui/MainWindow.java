package rick.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import rick.Rick;
import rick.Ui;

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

    private Rick Rick;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/morty.png"));
    private Image rickImage = new Image(this.getClass().getResourceAsStream("/images/rick.png"));

    private Image bgImage = new Image(this.getClass().getResourceAsStream("/images/background.png"));
    private BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true,
            true, false);
    private BackgroundImage backgroundImage = new BackgroundImage(bgImage, null, null, null, backgroundSize);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.backgroundProperty().set(new Background(backgroundImage));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("Hi Rick!", userImage),
                DialogBox.getRickDialog("Morty, cut the chit chat and give me a command now!", rickImage));
    }

    public void setRick(Rick d) {
        Rick = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Rick's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = Rick.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRickDialog(response, rickImage));
        if (input.equals("bye")) {
            PauseTransition waitForExit = new PauseTransition(Duration.seconds(2.0));
            waitForExit.setOnFinished((event) -> System.exit(0));
            waitForExit.play();
        }
        userInput.clear();
    }
}