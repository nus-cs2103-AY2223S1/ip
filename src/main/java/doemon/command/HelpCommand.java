package doemon.command;

import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.TaskList;

/**
 * Command to get help string.
 */
public class HelpCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        return response.helpString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
