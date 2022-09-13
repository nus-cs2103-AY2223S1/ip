import duke.DukeException;
import duke.Parser;
import duke.UI;
import duke.command.ByeCommand;
import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationTargetException;

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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/captain.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(UI.welcomeResponse(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        c.execute(duke.getTaskList(), duke.getStorage());
        return c.getResponse();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException{
        try {
            String userText = userInput.getText();
            String dukeText = getResponse(userInput.getText());

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getDukeDialog(dukeText, dukeImage)
            );
            userInput.clear();
        } catch (NullPointerException e) {
            System.out.println("EXCEPTION AT handleUserInput");
            e.printStackTrace();
        }
    }


}