package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.TaskDoesNotExistException;
import seedu.duke.list.TaskList;
import seedu.duke.Ui.Ui;

/**
 * Class to execute mark command
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the given task as done
     * @param list
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        int len = list.size();
        if (index >= len) {
            throw new TaskDoesNotExistException(index);
        }

        boolean success = list.get(index).markDone();
        return Ui.marked(list, index, success);
    }
}
