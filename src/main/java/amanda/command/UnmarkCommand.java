package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

/**
 * UnMarkCommand is a command that unMark a task that is done.
 */
public class UnMarkCommand extends Command {

    private final Task task;
    private final int idx;
    /**
     * Constructor for UnmarkCommand class
     */
    public UnMarkCommand(Task task, int idx) {
        this.task = task;
        this.idx = idx;
    }

    /**
     * Mark the task at the index provided in the constructor as done in the current task list.
     * @param tasks the current task list.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, StoreManager store) {
        Ui.unMarkResponse(task, task.getState());
        TaskList.getList().get(idx - 1).undoTask(); // mark the current task as not done.
        Ui.addResponse(task.toString());
        store.store(); // update the storage with the new state of the task.
    }
}
