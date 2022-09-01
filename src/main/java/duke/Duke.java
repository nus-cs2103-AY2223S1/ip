package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static final String FILE_PATH = "Duke/duke.txt";
    private Storage storage;
    private List tasks;
    private Ui ui;

    /**
     * Sets up the required objects and loads up the data from the storage file.
     *
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(FILE_PATH);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
        try {
            tasks = new List();
            storage.load(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            throw new RuntimeException(e);
        }
    }

    /** Runs the program until termination. */
    public static void main(String... args) {

        new Duke().run();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit
     * command.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
