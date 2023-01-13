package kirby.ui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import kirby.Kirby;
import kirby.exceptions.KirbyInvalidCommandException;
import kirby.exceptions.KirbyMissingArgumentException;

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
    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/mario.png")));
    private final Image kirbyImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/kirby_picture.png")));

    /**
     * Initialises the components for GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Assigns instance of kirby.
     */
    public void setKirby(Kirby kirby) {
        this.kirby = kirby;
    }

    /**
     * Displays welcome message on the GUI.
     *
     * @param welcomeMessage Default welcome message.
     */
    public void showWelcome(String welcomeMessage) {
        dialogContainer.getChildren().addAll(
                DialogBox.getKirbyDialog(welcomeMessage, kirbyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Kirby's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @throws KirbyMissingArgumentException Invalid arguments passed.
     * @throws KirbyInvalidCommandException Invalid commands passed.
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
