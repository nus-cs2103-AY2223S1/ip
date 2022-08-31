package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Abstraction to mark a task as complete.
 *
 * @author Kartikeya
 */
public class MarkCommand implements Command {
    private final String index;

    public MarkCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks task at given index as complete, and shows output to user.
     */
    @Override
    public String execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        try {
            return itemList.mark(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            throw new DukeException("Please attach a valid number to the command.");
        }
    }
}
