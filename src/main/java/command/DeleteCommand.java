package command;

import exception.DorisException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates a user instruction to delete a saved task.
 *
 * @author Marcus Low
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a delete command to remove a task from a list.
     *
     * @param index Index of task in the task list.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deleted = tasks.delete(this.index);
            ui.showDeleted(tasks, deleted);
            storage.save(tasks);
        } catch (DorisException e) {
            ui.showError(e);
        }
    }
}
