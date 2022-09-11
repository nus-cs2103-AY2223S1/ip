package duke.command;

import duke.exception.IllegalTaskException;
import duke.logic.TaskList;

/**
 * MarkCommand is a command for Duke to mark a task.
 *
 * @author totsukatomofumi
 */
public class MarkCommand extends Command {
    /** Task list the command will mark a task from. */
    private TaskList taskList;

    /** Zero-based index of the task to mark. */
    private int queryIndex;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param queryIndex the zero-based index of the task to mark.
     * @throws IllegalTaskException If the task specified to mark does not exist.
     */
    public MarkCommand(TaskList taskList, int queryIndex) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(queryIndex)) {
            this.queryIndex = queryIndex;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    @Override
    public String get() {
        return "Nice! I've marked this task as done:\n"
                + taskList.markTask(queryIndex).toString();
    }
}
