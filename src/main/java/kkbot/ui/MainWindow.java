package kkbot.ui;

import kkbot.KKBot;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow that provides the layout for the other controls.
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

    private KKBot kkbot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image kKBotImage = new Image(this.getClass().getResourceAsStream("/images/kkbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKKBot(KKBot b) {
        kkbot = b;
        String loadError = kkbot.showLoadError();
        if (!loadError.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getKKBotDialog(loadError, kKBotImage));
        }
        dialogContainer.getChildren().add(DialogBox.getKKBotDialog(kkbot.greet(), kKBotImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = kkbot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKKBotDialog(response, kKBotImage)
        );
        userInput.clear();
    }
}
