package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates marking a task as incomplete.
 *
 * @author Kartikeya
 */
public class UnmarkCommand implements Command {
    private final String index;

    public UnmarkCommand(String index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     * Marks task at given index as incomplete, and shows output to user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        try {
            ui.showToUser(itemList.unmark(Integer.parseInt(index)));
        } catch (NumberFormatException e) {
            throw new DukeException("Please attach a valid number to the command.");
        }
    }
}
