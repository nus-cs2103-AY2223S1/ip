package commands;

import data.TaskList;
import storage.Storage;

/**
 * Abstract class for all Duke commands to inherit.
 */
public abstract class Command {
    /**
     * Execute the particular command by running methods on TaskList, UI and Storage.
     *
     * @param tasks   Task list.
     * @param storage Storage object to save each action.
     * @return Output of command
     **/
    public abstract String execute(TaskList tasks, Storage storage);

    public boolean isExit() {
        return false;
    }
}
