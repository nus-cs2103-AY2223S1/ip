package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
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
        if (index > list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        boolean success = list.get(index).markDone();
        return Ui.marked(list, index, success);
    }
}
