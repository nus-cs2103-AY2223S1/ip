package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a personal task tracker, with local storage capabilities.
 *
 * @author Tan Jun Wei
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Duke instance.
     *
     * @param filePath The path of the storage file.
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
     * Returns the response to the user input.
     *
     * @param input The user input.
     * @return The response from duke.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            ui.showLine();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
        if (isExit) {
            System.exit(0);
        }
        return ui.getOutput();
    }
}
