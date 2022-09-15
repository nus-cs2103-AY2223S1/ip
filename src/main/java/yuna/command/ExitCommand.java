package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Command that exits the bot.
 *
 * @author Bryan Ng Zi Hao
 */
public class ExitCommand extends Command {

    /**
     * Displays the exit message.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        return ui.formatMessage("See you later :)");
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return false;
    }
}
