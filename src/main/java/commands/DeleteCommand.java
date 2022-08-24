package commands;

import storage.Storage;

// Import Tasks
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import ui.Ui;


/**
 * Encapsulates a user instruction to delete a saved task.
 *
 * @author fannyjian
 */
public class DeleteCommand extends Command {
    private int num;

    /**
     * Initialises a command to delete a Task.
     *
     * @param num Index of Task in list displayed to user.
     */
    public DeleteCommand(int num) {
        this.num = num - 1;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(this.num);
        ui.showDeleted(tasks, task);
        storage.updateStorage(tasks);
    }
}
