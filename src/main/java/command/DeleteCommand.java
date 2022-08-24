package command;

import storage.Storage;

import exception.LunaException;

// Import Tasks
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

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
        try {
            Task task = tasks.delete(this.num);
            ui.showDeleted(tasks, task);
            storage.updateStorage(tasks);
        } catch (LunaException e) {
            ui.showError(e);
        }
    }
}
