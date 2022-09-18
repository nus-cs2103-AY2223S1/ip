package duke.gui;

import java.util.function.Supplier;

import duke.command.ByeCommand;
import duke.logic.Parser;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke.\nWhat can I do for you?";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        this.dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage));
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userInput.clear();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        Supplier<String> command = parser.parse(input);
        String response = command.get();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
        if (command instanceof ByeCommand) { //bye gives 2 sec pause before closing program
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}