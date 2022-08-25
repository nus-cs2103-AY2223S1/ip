package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.exception.DukeException;

public abstract class Command {
    Storage storage;
    UI ui;
    TaskList taskList;

    /**
     * Constructs a specified Command task for Duke.
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
