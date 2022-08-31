package duke.gui;

import duke.ExecuteResult;
import duke.Parser;
import duke.modules.Todos;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static duke.Ui.say;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/media/Damith.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/media/Botto.png"));

    // initialize plugins

    Todos todos = new Todos();

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // intro string
        List<String> messages = new ArrayList<>(say("Hello, Duke here! What can I do for you?"));

        // initialize plugins
        Todos todos = new Todos();
        messages.addAll(todos.init());

        for (String message : messages) {
            dialogContainer.getChildren().add(ChatMessage.getDukeDialog(message, dukeImage));
        }
    }

    /**
     * Handles an input from the user.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        ExecuteResult result = Parser.execute(input, todos);
        dialogContainer.getChildren().addAll(
                ChatMessage.getUserDialog(input, userImage),
                ChatMessage.getDukeDialog(String.join("\n", result.getReply()), dukeImage)
        );
        userInput.clear();
    }
}
