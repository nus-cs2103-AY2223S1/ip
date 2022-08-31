package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    /**
     * Execute the particular command by running methods on TaskList, UI and Storage.
     *
     * @param tasks   Task list.
     * @param ui      UI for input/output.
     * @param storage Storage object to save each action.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
