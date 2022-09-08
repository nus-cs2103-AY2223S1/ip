package duke.command;

import duke.exception.OutOfRangeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * Created when user inputs "delete".
 */
public class DeleteCommand extends Command {
    private int num;
    /**
     * Constructor for a delete command.
     * @param num The number of the associated task.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Deletes the specified task in the task list.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     * @throws OutOfRangeException If number specified is out of range of the task list.
     */
    @Override
    public String execute(Storage storage, UI ui, TaskList taskList) throws OutOfRangeException {
        if (num > taskList.size() || num < 0) {
            throw new OutOfRangeException();
        }
        String temp = taskList.getTask(num - 1).getDescription();
        taskList.delete(num - 1);
        storage.save(taskList.list());
        return "Noted, I've removed this task:\n" + temp
            + "\nNow you have " + taskList.size() + " tasks in the list";
    }
}
