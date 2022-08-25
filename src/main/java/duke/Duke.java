package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.FileDoesNotExistException;
import duke.exception.StorageOperationException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Duke class that encapsulates the information of Duke
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object
     */
    public Duke() {
        ui = new Ui();

        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * To run the Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean bye = false;
        while (!bye) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                bye = c.bye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry of the Duke application
     * @param args user input.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
