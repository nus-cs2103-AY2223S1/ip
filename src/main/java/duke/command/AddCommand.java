package duke.command;

import duke.exception.TaskDuplicatedException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a AddCommand object.
 */
public abstract class AddCommand extends Command {
    public static final String TASK_ADD = "Alright, I have noted it down, Sheldon.";
    private Task task;

    /**
     * Constructs AddCommand object with task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes AddCommand by adding the task to tasks.
     *
     * @param tasks task to be added to tasks.
     * @param storage update the storage when task is added to tasks.
     * @return task command message
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskDuplicatedException {
        tasks.addTask(task);
        storage.update(tasks);
        return String.format("%s\n%s", TASK_ADD, task);
    }
}
