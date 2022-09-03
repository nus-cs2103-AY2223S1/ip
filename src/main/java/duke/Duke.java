package duke;

import command.Command;
import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.DialogBox;
import ui.Ui;

/**
 * <h1>Duke class</h1>
 * Main class of the chat bot that links the
 * Storage, TaskList and Ui together.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Creates the Duke object.
     *
     * @param ui the User Interface that prints out the output.
     * @param dialogContainer the VBox to add the dialog to.
     */
    public Duke(Ui ui, VBox dialogContainer) {
        this.ui = ui;
        storage = new Storage("data", "duke.txt");
        try {
            tasks = new TaskList(storage.readSavedTasks());
        } catch (DukeException e) {
            ui.sayErrorMessageWithoutUserInput(e.getMessage(), dialogContainer);
        }
        parser = new Parser(tasks, ui);
        ui.greet(dialogContainer);
    }

    /**
     * Parses and executes the user's input
     *
     * @param trimmedUserInput user input String that is trimmed.
     * @param userDialog the Dialog Box containing the user's input to be added to the Vbox.
     * @param dialogContainer the VBox to add the dialog to.
     */
    public void execute(String trimmedUserInput, DialogBox userDialog, VBox dialogContainer) {
        try {
            Command command = parser.parse(trimmedUserInput);
            command.execute(dialogContainer, userDialog);
            if (command.isExit()) {
                try {
                    storage.writeToFile(tasks);
                    ui.sayGoodbye(dialogContainer, userDialog);
                    executeDelay();
                } catch (DukeException e) {
                    ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
                }
            }
        } catch (DukeException e) {
            ui.sayErrorMessageWithUserInput(e.getMessage(), dialogContainer, userDialog);
        }
    }

    private void executeDelay() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
