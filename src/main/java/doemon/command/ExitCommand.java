package doemon.command;

import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.TaskList;

/**
 * Command to exit the Doemon chat bot.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        return response.exitString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
