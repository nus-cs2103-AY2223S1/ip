package ui;

import command.Command;
import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    /**
     * Creates a MainWindow object.
     *
     * @param storage storage to read and write saved tasks.
     * @param ui the User Interface that prints out the output.
     * @param tasks list of Tasks the user currently has.
     * @param sendButton button that will update the Ui.
     * @param userInput the user's input that will be included in the Ui.
     * @param parser Parser that parses the input to obtain the Command to be executed.
     * @param dialogContainer the VBox to be updated with the dialogs.
     */
    public MainWindow(Storage storage, Ui ui, TaskList tasks, Button sendButton, TextField userInput,
                      Parser parser, VBox dialogContainer) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.sendButton = sendButton;
        this.userInput = userInput;
        this.parser = parser;
        this.dialogContainer = dialogContainer;

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String trimmedUserInput = userInput.getText().trim();
        Label userText = new Label(trimmedUserInput);
        userText.setPadding(new Insets(0, 10, 0, 10));
        DialogBox userDialog = DialogBox.getUserDialog(userText, new ImageView(userImage));
        try {

            Command command = parser.parse(trimmedUserInput);
            command.execute(dialogContainer, userDialog);
            if (command.isExit()) {
                try {
                    storage.writeToFile(tasks);
                    ui.sayGoodbye(dialogContainer, userDialog);
                    PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
                    delay.setOnFinished(event -> Platform.exit());
                    delay.play();
                } catch (DukeException e) {
                    ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
                }
            }
        } catch (DukeException e) {
            ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
        } finally {
            userInput.clear();
        }
    }
}
