package duke;

import duke.command.Command;
import duke.common.exception.DukeException;
import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Main class for Duke Application
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Generates a Duke object that uses file located at filePath
     *
     * @param filePath File path for disk storage
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
