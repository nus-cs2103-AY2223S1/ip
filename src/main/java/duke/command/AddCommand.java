package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Encapsulation of the command of adding tasks to a list.
 *
 * @author Sun Ruoxin
 */
public class AddCommand extends Command {
    /** The task to be added to the list. */
    protected Task task;

    /**
     * Class constructor.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * Adds the task to the list.
     * Rewrites the file in the hard disk.
     * Shows the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(task);
        storage.writeFile(tasks);
        return "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }
}
