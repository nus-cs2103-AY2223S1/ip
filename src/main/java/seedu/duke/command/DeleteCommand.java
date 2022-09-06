package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;

/**
 * Class for executing delete commands
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the given task from the task list
     * @param list list from which to delete the task
     * @throws DukeException
     */
    public void execute(TaskList list) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("There is no task " + index + " just yet, Master.");
        }
        Task temp = list.get(index);
        list.remove(index);
        Ui.deleted(temp);
    }
}
