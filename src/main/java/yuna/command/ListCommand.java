package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Command that lists out all the tasks.
 *
 * @author Bryan Ng Zi Hao
 */
public class ListCommand extends Command {

    /**
     * List all the tasks out.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        if (taskList.size() > 0) {
            String output = "Your schedule!\n";
            assert output.length() > 0 : "The output of execute should always return a message.";
            return output + taskList.printString();
        } else {
            return ui.formatMessage("You have nothing in your schedule! :)");
        }
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
