package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * This class encapsulates a mark command from the user.
 */
public class MarkCommand extends Command {
    // Solution below adapted from https://github.com/teikjun/duke
    public static final String COMMAND_WORD = "mark";

    private TaskList taskList;
    private int pos;

    /**
     * Creates a MarkCommand with the given TaskList and position.
     *
     * @param taskList The TaskList.
     * @param pos      The position.
     * @throws DukeException If the position is not in the TaskList.
     */
    public MarkCommand(TaskList taskList, int pos) throws DukeException {
        if (pos < 0 || pos > taskList.size() - 1) {
            throw new DukeException("The list doesn't have a task with that number.");
        }
        this.taskList = taskList;
        this.pos = pos;

    }

    /**
     * Marks the Task at the position of the TaskList as done.
     *
     * @return A String signalling that the Task has been marked as done.
     */
    @Override
    public String execute() {
        Task task = this.taskList.setDone(this.pos, true);
        return "Nice! I've marked this task as done:\n"
                + task;
    }
}
