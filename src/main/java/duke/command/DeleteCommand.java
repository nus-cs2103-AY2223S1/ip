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

    /**
     * Removes the specified task.
     */
    @Override
    public void run() {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.remove(query).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

}