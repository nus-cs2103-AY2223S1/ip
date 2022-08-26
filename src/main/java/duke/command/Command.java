package duke.command;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * A Command task used by the app.
 */
public abstract class Command {
    protected Storage storage;
    protected UI ui;
    protected TaskList taskList;

    /**
     * Constructs a specified Command command for Duke.
     *
     * @param storage Storage of the current Duke program.
     * @param ui UI of the current Duke program.
     * @param taskList Tasklist of the current Duke program.
     */
    public Command(Storage storage, UI ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     *  Constructs a specified Command task for Duke that does not require any Duke utility classes.
     */
    public Command() {

    }

    /**
     * Generates the output of the associated Command task.
     *
     * @param storage Storage of the current Duke program.
     * @param ui UI of the current Duke program.
     * @param taskList Tasklist of the current Duke program.
     * @throws DukeException If parsed input is invalid.
     */
    public abstract void execute(Storage storage, UI ui, TaskList taskList) throws DukeException;
}
