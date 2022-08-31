package kirby.ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kirby.Kirby;
import kirby.commands.ExitCommand;
import kirby.exceptions.KirbyInvalidCommandException;
import kirby.exceptions.KirbyMissingArgumentException;

import java.io.IOException;

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

    private Kirby kirby;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/mario.png"));
    private Image kirbyImage = new Image(this.getClass().getResourceAsStream("/images/kirby_picture.png"));
    private String output = ("I loved talking to you ･ω･\n" + "Hope to see you again!");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKirby(Kirby kirby) {
        this.kirby = kirby;
    }

    public void showWelcome(String welcomeMessage) {
        dialogContainer.getChildren().addAll(
                DialogBox.getKirbyDialog(welcomeMessage, kirbyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws KirbyMissingArgumentException, KirbyInvalidCommandException {
        String input = userInput.getText();
        String response = kirby.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKirbyDialog(response, kirbyImage)
        );
        userInput.clear();
    }
}
