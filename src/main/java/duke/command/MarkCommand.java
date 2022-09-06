package duke.command;

import duke.exception.OutOfRangeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "mark".
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructor for a mark command.
     * @param num The number of the associated task.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Marks the specified task as done.
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
        taskList.getTask(num - 1).setDone(true);
        storage.save(taskList.list());
        return "Nice! I've marked this task as done:\n" + taskList.getTask(num - 1);
    }
}
