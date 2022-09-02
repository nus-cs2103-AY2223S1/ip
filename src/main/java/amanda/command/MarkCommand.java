package main.java.amanda.command;

import main.java.amanda.ui.Ui;
import main.java.amanda.manager.TaskList;
import main.java.amanda.manager.StoreManager;
import main.java.amanda.exception.AmandaException;

/**
 * MarkCommand is a command that marks a task as done
 */
public class MarkCommand extends Command {

    /**
     * Constructor for MarkCommand class.
     * @param taskNo the index of the task which the user wants to mark as done.
     */
    public MarkCommand(int taskNo) {
        super(null, taskNo);
    }

    /**
     * Mark the task at the index provided in the constructor as done in the current task list.
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
        tasks.getList().get(taskNo - 1).doTask(); // mark the current task as done.
        store.store(tasks); // update the storage with the new state of the task.
        ui.showMarkCommand(tasks,taskNo); // print messages for MarkCommand.
    }
}
