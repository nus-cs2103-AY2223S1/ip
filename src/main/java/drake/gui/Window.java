package drake.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import drake.DrakeException;
import drake.Parser;
import drake.Storage;
import drake.TaskList;
import drake.Ui;
import drake.commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the chat window.
 * Inspired by parnikkapore's PR.
 */
public class Window extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/media/lostsoul.png"));
    private final Image drakeImage = new Image(this.getClass().getResourceAsStream("/media/drake.png"));

    // initialize plugins

    private TaskList taskList;
    private Storage storage;
    private final Ui ui = new Ui();

    public Window() throws DrakeException, IOException {
    }

    /**
     * Initializes the GUI Window.
     * @throws DrakeException When the user enters incorrect input.
     * @throws IOException When IO encounters an issue.
     */
    @FXML
    public void initialize() throws DrakeException, IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // intro string
        List<String> messages = new ArrayList<>(ui.chatBubbleText(ui.replyWelcome()));

        // initialize plugins
        storage = new Storage();
        taskList = new TaskList(storage.fileToList());

        for (String message : messages) {
            dialogContainer.getChildren().add(ChatMessage.getDrakeDialog(message, drakeImage));
        }
    }

    /**
     * Handles an input from the user.
     */
    @FXML
    private void handleUserInput() throws DrakeException, IOException {
        String input = userInput.getText();
        Command command = Parser.parse(input);
        List<String> reply = command.execute(taskList, ui, storage);
        dialogContainer.getChildren().add(ChatMessage.getUserDialog(input, userImage));

        dialogContainer.getChildren().add(
                ChatMessage.getDrakeDialog(String.join("\n", reply), drakeImage));

        userInput.clear();
    }
}
