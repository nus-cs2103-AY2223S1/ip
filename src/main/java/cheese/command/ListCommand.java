package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Response;

/**
 * Represents a user command to display the list.
 */
public class ListCommand extends Command {
    /**
     * Executes operation to display the list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Response.getTaskListMessage(taskList);
    }
}
