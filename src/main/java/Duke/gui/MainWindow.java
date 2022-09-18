package Duke.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Duke.*;
import Duke.commands.Command;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    private TaskList taskList;
    private Storage storage;
    private final Ui ui = new Ui();

    public MainWindow() throws DukeException, IOException {
    }

    @FXML
    public void initialize() throws IOException {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        List<String> messages = new ArrayList<>(ui.chatBox(ui.showLogo()));

        taskList = new TaskList(storage.fileToList());
        storage = new Storage();

        for (String message : messages) {
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
        }
    }



    /**
         * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
         * the dialog container.
         */
        @FXML
        private void handleUserInput() throws DukeException, IOException {
            String input = userInput.getText();
            Command command = Parser.parse(input);
            List<String> reply = command.execute(taskList, ui, storage);
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            List<String> text = new ArrayList<>();
            for (String line : reply) {
                text.add(line);
                if (text.size() >= 5) {
                    dialogContainer.getChildren().add(
                            DialogBox.getDukeDialog(String.join("\n", text), dukeImage));
                    text.clear();
                }
            }
            if (text.size() > 0) {
                dialogContainer.getChildren().add(
                        DialogBox.getDukeDialog(String.join("\n", text), dukeImage));
            }

            userInput.clear();
        }
}




