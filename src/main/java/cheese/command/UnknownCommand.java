package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Response;

/**
 * Represents a user command that is unrecognized.
 */
public class UnknownCommand extends Command {
    /**
     * Executes operation to display error message.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Response.getUnknownCommandMessage();
    }
}
