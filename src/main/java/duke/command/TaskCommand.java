package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a TaskCommand object.
 */
public abstract class TaskCommand extends Command {
    public static final String TASK_ADD = "Got it. I've added this task";
    private Task task;

    /**
     * Constructs TaskCommand object with task.
     *
     * @param task Task to be added.
     */
    public TaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes TaskCommand by adding the task to tasks.
     *
     * @param tasks task to be added to tasks.
     * @param storage update the storage when task is added to tasks.
     * @return task command message
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.addTask(task);
        storage.update(tasks);
        return String.format("%s\n%s", TASK_ADD, task);
    }
}
