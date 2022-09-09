package duke.command;

import duke.ClientList;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents a command to save task list in save file.
 */
public class SaveTaskListCommand extends Command {

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param storage  files storing task list.
     * @param clientList client list.
     * @return nothing.
     */
    public String execute(TaskList taskList, Storage storage, ClientList clientList) {
        storage.saveTaskList(taskList);
        return null;
    }
}
