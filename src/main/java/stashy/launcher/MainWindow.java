package stashy.launcher;

import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import stashy.Stashy;
import stashy.commands.Command;
import stashy.data.exception.StashyException;
import stashy.parser.Parser;

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

    private Stashy stashy;
    private boolean isExit = false;
    private Random rng = new Random();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image stashyImageNormal = new Image(this.getClass().getResourceAsStream("/images/genenormal.png"));
    private Image stashyImageThinking = new Image(this.getClass().getResourceAsStream("/images/genethink.png"));
    private Image stashyImageCool = new Image(this.getClass().getResourceAsStream("/images/genecool.png"));
    private Image[] stashyImages = { stashyImageNormal, stashyImageThinking, stashyImageCool };

    /**
     * Initialization method to be run upon startup.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image randomStashyImage = stashyImages[rng.nextInt(stashyImages.length)];
        dialogContainer.getChildren().addAll(
            DialogBox.getStashyDialog(Stashy.showWelcomeMessageGui(), randomStashyImage, false)
        );
    }

    public void setStashy(Stashy s) {
        stashy = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Stashy's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Image randomStashyImage = stashyImages[rng.nextInt(stashyImages.length)];
        boolean isError = false;

        String input = userInput.getText().strip();
        if (input.isEmpty()) {
            return;
        }
        String response;
        try {
            if (!isExit) {
                Command c = Parser.parseCommand(input, false);
                response = stashy.executeCommandReturnString(c);
                isExit = c.isExit();
            } else {
                return;
            }
        } catch (StashyException se) {
            isError = true;
            response = se.getMessage();
        }

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage, false),
            // Picks a random image for Stashy
            DialogBox.getStashyDialog(response, randomStashyImage, isError)
        );
        userInput.clear();
    }
}
