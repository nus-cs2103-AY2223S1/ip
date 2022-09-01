package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private boolean isExit = false;
    private Storage storage;
    private final List tasks;
    private final Ui ui;

    /**
     * Sets up the required objects and loads up the data from the storage file.
     *
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage();
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
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.shouldExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the main logic of the program.
     *
     * @return The response from executing the command.
     * @throws DukeException If there are no commands.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            isExit = c.shouldExit();
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
        assert !output.equals("");
        return output;
    }

    /**
     * Checks if the last user command is an exit command.
     *
     * @return True if the last user command is an exit command, false otherwise.
     */
    public boolean shouldExit() {
        return isExit;
    }

}
