package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * An abstract class the encapsulates the command action.
 */
public abstract class Command {
    /**
     * Executes the command instructed to Duke.
     *
     * @param tasks   Current task list that is stored in Duke.
     * @param storage Current storage object that is stored in Duke.
     * @param ui      Current ui object that is stored in Duke.
     * @throws DukeException Exception will be thrown for unexpected scenarios.
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

}
