package duke.main;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.tasks.TaskList;

/**
 * Duke is our helper which manages the commands
 */
public class Duke {

    protected final Storage storage;
    /* The tasklist keeps track of all the tasks added */
    protected TaskList tasks;
    private boolean isExit = false;

    /**
     * Initialises TaskList based on whether data file specified in filePath is valid
     *
     * @param filePath Filepath where data file is stored in
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns exit field of Duke
     *
     * @return exit field of Duke
     */
    public boolean getExit() {
        return this.isExit;
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            this.isExit = c.isExit();
            return c.execute(tasks, storage);
        } catch (IllegalArgumentException e) {
            return "Unknown command received...";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
