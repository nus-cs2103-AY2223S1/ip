package duke.command;

import duke.exception.IllegalTaskException;
import duke.logic.TaskList;

/**
 * UnmarkCommand is a command for Duke to unmark a task.
 *
 * @author totsukatomofumi
 */
public class UnmarkCommand extends Command {
    /** Task list the command will unmark a task from. */
    private TaskList taskList;

    /** Zero-based index of the task to unmark. */
    private int query;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param query the zero-based index of the task to unmark.
     * @throws IllegalTaskException If the task specified to unmark does not exist.
     */
    public UnmarkCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    @Override
    public String get() {
        return "OK, I've marked this task as not done yet:\n"
                + taskList.unmarkTask(query).toString();
    }
}
