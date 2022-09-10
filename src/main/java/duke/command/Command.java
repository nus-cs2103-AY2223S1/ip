package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates a command given to Artemis.
 *
 * @author Kartikeya
 */
public interface Command {
    /**
     * Executes the command action.
     *
     * @param itemList list of tasks
     * @param storage  loading/saving from local storage
     * @throws DukeException if input is erroneous
     */
    public abstract String execute(TaskList itemList, Storage storage)
            throws DukeException;
}
