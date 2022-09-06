package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Class to execute unmark command
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the given task as undone
     * @param list
     * @throws DukeException
     */
    @Override
    public void execute(TaskList list) throws DukeException {
        if (index > list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        boolean success = list.get(index).markUndone();
        Ui.unmarked(list, index, success);
    }
}
