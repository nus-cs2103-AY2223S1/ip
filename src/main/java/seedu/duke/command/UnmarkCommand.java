package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskDoesNotExistException;
import seedu.duke.list.TaskList;
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
        int len = list.size();
        if (index >= len) {
            throw new TaskDoesNotExistException(index);
        }

        boolean success = list.get(index).markUndone();
        return Ui.unmarked(list, index, success);
    }
}
