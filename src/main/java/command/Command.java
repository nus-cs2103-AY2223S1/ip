package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;


/**
 * Encapsulates the commands the users will input
 *
 * @author Marcus Low
 */
public abstract class Command {

    /**
     * Updates task list to reflect the command to execute.
     * Calls the UI to inform users that the command has been performed successfully.
     * Calls the storage to update tasks saved in the hard disk.
     *
     * @param tasks   Lists of task to do
     * @param ui      User interface
     * @param storage Storage of the tasks saved in the hard disk.
     * @return
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether the command leads to program termination.
     *
     * @return Boolean.
     */
    public boolean isExit() {
        return false;
    }
}
