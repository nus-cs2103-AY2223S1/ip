package commands;

import exception.FredException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Delete command for deleting item from taskList.
 */
public class DeleteCommand extends Command {

    protected int index;

    /**
     * Add DeleteCommand
     * @param index index of task to be deleted
     */
    public DeleteCommand(int index) {
        isExit = false;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        String taskToDelete = tasks.getTask(index).toString();
        tasks.delete(index);
        ui.storeMessage("Noted. I've removed this task:\n"
                + taskToDelete
                + "\n"
                + "Now you have "
                + tasks.size()
                + " tasks in your list.\n");
    }
}
