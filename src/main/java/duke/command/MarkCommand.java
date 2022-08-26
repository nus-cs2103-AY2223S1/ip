package duke.command;

import duke.logic.TaskList;
import duke.exception.IllegalTaskException;

/**
 * MarkCommand is a command for Duke to mark a task.
 *
 * @author totsukatomofumi
 */
public class MarkCommand extends Command {
    /** Task list the command will mark a task from. */
    private TaskList taskList;

    /** Zero-based index of the task to mark. */
    private int query;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list the command will modify.
     * @param query the zero-based index of the task to mark.
     * @throws IllegalTaskException If the task specified to mark does not exist.
     */
    public MarkCommand(TaskList taskList, int query) throws IllegalTaskException {
        this.taskList = taskList;
        if (this.taskList.exists(query)) {
            this.query = query;
        } else {
            throw new IllegalTaskException("Task does not exist.");
        }
    }

    /**
     * Marks the specified task.
     */
    @Override
    public void run() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.markTask(query).toString());
    }
}