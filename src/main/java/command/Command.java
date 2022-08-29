package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Encapsulates the type of instruction made by user.
 *
 * @author fannyjian
 */
public abstract class Command {

    /**
     * Updates task list to reflect the command carried out.
     * Calls the UI to inform users that the command has been performed successfully.
     * Calls Storage to update tasks saved in hard disk.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Indicates whether the command leads to program exiting.
     *
     * @return Boolean.
     */
    public abstract boolean isExit();
}
