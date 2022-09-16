package duke.frontend;

import duke.Duke;
import duke.Parser;
import duke.Reminder;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    @FXML
    private Label label;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/remy.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/linguini.png"));

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Initialize the program with a new window pop-up launching
     */
    @FXML
    public void initialize() throws DukeException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetingMessage = new Ui().getGreetingMessage();
        String reminder = new Reminder().getReminder();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetingMessage, dukeImage),
                DialogBox.getDukeDialog(reminder, dukeImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws DukeException {
        try {
            String input = userInput.getText();
            assert !input.equals("") : "Input cannot be empty.";
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            Command command = Parser.parse(input);
            if (command.isExit()) {
                this.handleExit();
            }
            userInput.clear();
        } catch (DukeException | AssertionError e) {
            System.out.println("Input cannot be empty.");
        }
    }

    @FXML
    private void handleExit() {
        userInput.setDisable(true);
        sendButton.setDisable(true);
        assert userInput.isDisable() : "Text box is not disabled.";
        assert sendButton.isDisable() : "Send button is not disabled.";
    }

}
