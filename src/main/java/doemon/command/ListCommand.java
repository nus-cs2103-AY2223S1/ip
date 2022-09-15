package doemon.command;

import doemon.response.Response;
import doemon.storage.Storage;
import doemon.task.TaskList;

/**
 * Command to list all tasks currently recorded.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Response response, Storage storage) {
        return response.taskListString(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
