package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

/**
 * MarkCommand is a command that marks a task as done
 */
public class MarkCommand extends Command {

    private final Task task;
    private final int idx;

    /**
     * Constructor for MarkCommand class.
     */
    public MarkCommand(Task task, int idx) {
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
        Ui.markResponse(task, task.getState());
        TaskList.getList().get(idx - 1).doTask(); // mark the current task as done.
        Ui.addResponse(task.toString());
        store.store(); // update the storage with the new state of the task.
    }
}
