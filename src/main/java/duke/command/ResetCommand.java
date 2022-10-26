package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Encapsulates clearing of the user's task list.
 *
 * @author Kartikeya
 */
public class ResetCommand implements Command {
    /**
     * {@inheritDoc}
     * Clears user's task list and shows confirmation to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return itemList.clearList();
    }
}
