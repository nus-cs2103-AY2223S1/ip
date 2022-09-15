package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * The abstract class which recognizes the user's command.
 *
 * @author Bryan Ng Zi Hao
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    public abstract String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException;

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    public abstract boolean isRunning();
}
