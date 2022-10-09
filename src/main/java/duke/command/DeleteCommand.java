package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This class encapsulates a delete command from the user.
 */
public class DeleteCommand extends Command {
    // Solution below adapted from https://github.com/teikjun/duke
    public static final String COMMAND_WORD = "delete";

    private TaskList taskList;
    private int pos;

    /**
     * Creates a DeleteCommand with the given TaskList and position.
     *
     * @param taskList The TaskList.
     * @param pos      The position.
     * @throws DukeException If the position is not in the TaskList.
     */
    public DeleteCommand(TaskList taskList, int pos) throws DukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DukeException("The list doesn't have a task with that number.");
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
        return "I've removed this task:\n"
                + task
                + "\nNow you have " + this.taskList.size() + " tasks in your list.";
    }
}
