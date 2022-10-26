package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Abstraction to list all tasks stored in a TaskList.
 *
 * @author Kartikeya
 */
public class ListItemsCommand implements Command {
    /**
     * {@inheritDoc}
     * Shows contents of itemList to user.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) throws DukeException {
        return itemList.toString();
    }
}
