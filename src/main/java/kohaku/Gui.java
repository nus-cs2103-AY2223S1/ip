package kohaku;

import kohaku.commands.Command;
import kohaku.datastruct.Pair;
import kohaku.daveexceptions.DaveException;
import kohaku.parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */

public class Gui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/You.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Dave.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try{
            Pair<String, String> inputData = Parser.splitInputIntoCommand(input);
            Command command = Parser.dispatch(
                    inputData.getHead(), inputData.getTail(), Kohaku.getTasks()
            );
            String result  = command.execute();
            return result;
        } catch (DaveException e) {
            return e.toString();
        }
    }
}
