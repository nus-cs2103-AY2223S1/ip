package dwuke.command;

import dwuke.DwukeException;
import dwuke.task.Task;
import dwuke.task.TaskList;

/**
 * This class encapsulates a delete command from the user.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private TaskList taskList;
    private int pos;

    /**
     * Creates a DeleteCommand with the given TaskList and position.
     *
     * @param taskList The TaskList.
     * @param pos      The position.
     * @throws DwukeException If the position is not in the TaskList.
     */
    public DeleteCommand(TaskList taskList, int pos) throws DwukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DwukeException("da wist doesn't have a task with da number :3");
        }
        this.taskList = taskList;
        this.pos = pos;
    }

    /**
     * Deletes the Task at the position of the TaskList.
     *
     * @return A String signalling that the Task has been deleted.
     */
    @Override
    public String execute() {
        Task task = this.taskList.remove(this.pos);
        return "me've wemoved dis task:\n" + task
                + "\nnow you have " + this.taskList.size() + " tasks in da wist uWu";
    }
}
