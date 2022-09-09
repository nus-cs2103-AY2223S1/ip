package doemon.command;

import doemon.storage.Storage;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Commands executed by user-input.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The ui helper class.
     * @param storage The storage class for updating task data.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns a boolean indicating if the command is an exit command.
     *
     * @return Boolean indicating if the command is an exit command.
     */
    public abstract boolean isExit();
}
