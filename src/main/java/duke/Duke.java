package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class for Duke application
 */
public class Duke {
    /** Contains methods that deal with loading and saving of tasks */
    private Storage storage;
    /** List of tasks */
    private TaskList tasks;
    /** Deals with interactions with user */
    private Ui ui;

    /**
     * Constructor to initialize Storage, TaskList and Ui.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    
    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.printBorder();
                System.out.println();
                String fullCommand = ui.readCommand();
                System.out.println();
                ui.printBorder();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.printBorder();
    }

    /**
     * Main method for Duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
