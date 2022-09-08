package duke.command;

import duke.exception.OutOfRangeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "unmark".
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructor for an unmark command.
     * @param num The number of the associated task.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     * @throws OutOfRangeException If number specified is out of range of the task list.
     */
    @Override
    public String execute(Storage storage, UI ui, TaskList taskList) throws OutOfRangeException {
        if (!taskList.isInRange(num)) {
            throw new OutOfRangeException();
        }
        taskList.getTask(num - 1).setDone(false);
        storage.save(taskList.list());
        return "Ok, I've marked this task as not done yet:\n" + taskList.getTask(num - 1);
    }
}
