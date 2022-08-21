package dwuke.command;

import dwuke.DwukeException;
import dwuke.task.Task;
import dwuke.task.TaskList;

/**
 * This class encapsulates an unmark command from the user.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private TaskList taskList;
    private int pos;

    /**
     * Creates an UnmarkCommand with the given TaskList and position.
     *
     * @param taskList The TaskList.
     * @param pos      The position.
     * @throws DwukeException If the position is not in the TaskList.
     */
    public UnmarkCommand(TaskList taskList, int pos) throws DwukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DwukeException("da wist doesn't have a task with da number :3");
        }
        this.taskList = taskList;
        this.pos = pos;

    }

    /**
     * Marks the task at the position of the task list as not done.
     *
     * @return A String signalling that the task has been marked as not done.
     */
    @Override
    public String execute() {
        Task task = this.taskList.unmark(this.pos);
        return "okies, me've mawked dis task as not done yet:\n" + task;
    }
}
