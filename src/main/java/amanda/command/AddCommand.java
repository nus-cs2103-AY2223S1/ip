package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

/**
 * AddCommand is a command that adds new tasks to the current task list.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructor for AddCommand class.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task associated with this AddCommand to the current task list
     * and calls the ui to show the output to the user.
     * @param tasks the current task list.
     * @param store the store manager that write any changes to the storage file.
     */
    public void execute(TaskList tasks, StoreManager store) {
        TaskList.getList().add(this.task); // add the task to the current task list.
        store.store(); // update storage with new task addition .
        Ui.addTaskResponse(task);
    }
}
