package justin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import justin.command.Command;
import justin.command.ExitCommand;

/**
 * Represents the class which contains
 * the DialogBoxes of the chat between
 * user and the bot.
 * @author Justin Cheng.
 */
public class MainWindow extends AnchorPane {
    private static final String JUSTIN_DESCRIPTION = "Justin, the creator of this bot";
    private static final String YOUR_DESCRIPTION = "You, a nerd";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private JustinBot justinBot;

    private Image justin = new Image(this.getClass().getResourceAsStream("/images/gigachad.jpeg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/nerd.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setJustinBot(JustinBot j) {
        justinBot = j;
    }

    /**
     * Prints out the output in the form of
     * a DialogBox when user inputs in
     * a message.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            printDialog(input);
        } catch (DukeException e) {
            handleException(e.getMessage(), input);
        }
        userInput.clear();
    }

    /**
     * Prints out the welcome message when a
     * user just opens the application.
     * @param msg The welcome message to be
     *            printed out.
     */
    public void printWelcome(String msg) {
        DialogBox justinBox = DialogBox.getJustinDialog(msg, justin, JUSTIN_DESCRIPTION);
        dialogContainer.getChildren().add(justinBox);
    }

    /**
     * Responds to any exception by printing out
     * the DialogBox with the message of the exception.
     * @param exception The String message of the
     *                  exception.
     * @param input The erroneous input by the user.
     */
    public void handleException(String exception, String input) {
        DialogBox userBox = DialogBox.getUserDialog(input, user, YOUR_DESCRIPTION);
        DialogBox justinBox = DialogBox.getJustinDialog(exception, justin, JUSTIN_DESCRIPTION);
        justinBox.changeFormat();
        dialogContainer.getChildren().addAll(userBox, justinBox);
    }

    /**
     * Prints the DialogBox objects involved in
     * the input of the user and the response of
     * the bot.
     * @param userInput The user input.
     * @throws DukeException If the user input is
     * invalid.
     */
    public void printDialog(String userInput) throws DukeException {
        Command c = Parser.parse(userInput);
        String response = justinBot.getResponse(userInput);
        DialogBox userBox = DialogBox.getUserDialog(userInput, user, YOUR_DESCRIPTION);
        DialogBox justinBox = DialogBox.getJustinDialog(response, justin, JUSTIN_DESCRIPTION);
        dialogContainer.getChildren().addAll(userBox, justinBox);
        if (c instanceof ExitCommand) {
            System.exit(0);
        }
    }

    public void exit() {
        System.exit(0);
    }
}
