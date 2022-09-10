package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui.Ui;

/**
 * Class to execute unmark command
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     *
     * @param list
     * @return
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        if (index > list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        boolean success = list.get(index).markUndone();
        return Ui.unmarked(list, index, success);
    }
}
