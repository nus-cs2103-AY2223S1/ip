package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

    protected final Ui ui;
    protected final Storage storage;
    /**
     * The tasklist keeps track of all the tasks added
     */
    protected TaskList tasks;

    protected boolean isExit = false;

    /**
     * Initialises TaskList based on whether data file specified in filePath is valid
     *
     * @param filePath Filepath where data file is stored in
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, ui, storage);
        } catch (IllegalArgumentException e) {
            return "Unknown command received...";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
