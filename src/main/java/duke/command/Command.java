package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command given to Apollo.
 *
 * @author Kartikeya
 */
public interface Command {
    /**
     * Executes the command action.
     *
     * @param itemList list of tasks
     * @param ui       ui interactions with user
     * @param storage  loading/saving from local storage
     * @throws DukeException if input is erroneous
     */
    public abstract void execute(TaskList itemList, Ui ui, Storage storage)
            throws DukeException;
}
