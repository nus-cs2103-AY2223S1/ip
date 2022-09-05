package main.java.amanda.command;

import main.java.amanda.exception.AmandaException;
import main.java.amanda.manager.StoreManager;
import main.java.amanda.manager.TaskList;
import main.java.amanda.ui.Ui;

/**
 * DeleteCommand is a command that delete the specified task number from the current task list.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand class.
     */
    public DeleteCommand(int taskNo) {
        super(null, taskNo);
    }

    /**
     * Delete the corresponding task to the index input by the user from the current task list and calls the ui
     * to shew the output to the user.
     * @param tasks the current task list.
     * @param ui the current Ui.
     * @param store the store manager that write any changes to the storage file.
     * @throws AmandaException throw an exception if the index input by the user is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, StoreManager store) throws AmandaException {
        if (taskNo > tasks.getList().size() | taskNo <= 0) { // Check if index input by user is in the valid range.
            throw new AmandaException("     That task does not even exist. Why are you wasting my time?");
        }
        tasks.getList().remove(taskNo - 1); // remove the task from the list.
        store.store(tasks); // update the storage with the removal of the task.
        ui.showDeleteCommand(tasks); // print messages for Delete Command.
    }
}
