package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates deleting items from a TaskList object.
 *
 * @author Kartikeya
 */
public class DeleteCommand implements Command {
    private final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Removes given input index from itemList, and shows the resulting message to the user.
     */
    @Override
    public String execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        try {
            return itemList.deleteItem(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            throw new DukeException("Please attach a valid number to the command.");
        }
    }
}
