package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Represents the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs Duke object.
     * Initializes ui, storage and tasks.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke application.
     * Application runs until ExitCommand is executed.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String handleUserInput(String input) {
        try {
            Command c = parser.parse(input);
            String result = c.execute(tasks, ui, storage);
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Entry point of Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
