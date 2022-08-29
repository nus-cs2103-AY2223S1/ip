package duke.command;

//import util
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

//import task
import duke.task.Task;

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
     * @param ui display the task that was added to tasks.
     * @param storage update the storage when task is added to tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.update(tasks, ui);
        displayCommand(ui, TASK_ADD, task, tasks.getStatus());
    }
}
