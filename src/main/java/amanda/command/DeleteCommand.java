package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

/**
 * DeleteCommand is a command that delete the specified task number from the current task list.
 */
public class DeleteCommand extends Command {

    private final int idx;
    private final Task task;
    /**
     * Constructor for DeleteCommand class.
     */
    public DeleteCommand(Task task, int idx) {
        this.task = task;
        this.idx = idx;
    }

    /**
     * Delete the corresponding task to the index input by the user from the current task list and calls the ui
     * to shew the output to the user.
     * @param tasks the current task list.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, StoreManager store) {
        if (TaskList.getList().size() == 0) {
            Ui.addResponse("You have nothing else to delete lazy bum!");
        } else {
            TaskList.getList().remove(idx - 1); // remove the task from the list.
            store.store(); // update the storage with the removal of the task.
            Ui.deleteResponse(task);
        }
    }
}
