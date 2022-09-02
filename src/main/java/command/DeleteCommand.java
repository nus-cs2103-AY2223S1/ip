package command;

import exception.LunaException;
import storage.Storage;
// Import Tasks
import task.Task;
import task.TaskList;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.delete(this.num);
            storage.updateStorage(tasks);
            return ui.showDeleted(tasks, task);
        } catch (LunaException e) {
            return ui.showError(e);
        }
    }
}
