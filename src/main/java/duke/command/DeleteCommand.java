package duke.command;

import duke.exception.IllegalTaskException;
import duke.logic.TaskList;

/**
 * DeleteCommand is a command for Duke to delete a task.
 *
 * @author totsukatomofumi
 */
public class DeleteCommand extends Command {
    /** Task list the command will delete a task from. */
    private TaskList taskList;

    /** Zero-based index of the task to remove. */
    private int query;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param query the zero-based index of the task to remove.
     * @throws IllegalTaskException If the task specified to remove does not exist.
     */
    public DeleteCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    @Override
    public String get() {
        return "Noted. I've removed this task:\n"
                + taskList.remove(query).toString() + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.size());
    }
}
