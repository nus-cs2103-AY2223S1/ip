import duke.DukeException;
import duke.Parser;
import duke.UI;
import duke.command.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author Sheryl Kong (A0240686Y)
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

    /**
     * Initializes the main window of the program
     */

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
     * Generates a response according to user input
     *
     * @param input String
     * @throws DukeException when input is invalid
     */

    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        c.execute(duke.getTaskList(), duke.getStorage());
        return c.getResponse();
    }

    /**
     * Displays user input and generated Duke's output on main window.
     *
     * @throws DukeException when input is invalid
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