package mort.command;

import mort.exception.MortException;
import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks The task list.
     * @param ui The user interactions.
     * @param storage The task storage.
     * @throws MortException If the command cannot be executed.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MortException;
}
