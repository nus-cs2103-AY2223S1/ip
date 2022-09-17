package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for <code>AddCommand</code>.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a task into the task list.
     * @param tasks
     * @param storage
     */

    @Override
    public String getResponse(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        return "Yay! I added " + task.toString() + " to the list.\n"
                + "You have " + tasks.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Checks if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
