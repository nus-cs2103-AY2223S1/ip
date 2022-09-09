package duke.command;

import duke.ClientList;
import duke.Storage;
import duke.task.TaskList;

public class SaveClientListCommand extends Command {

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param storage  files storing task list.
     * @return
     */
    public String execute(TaskList taskList, Storage storage, ClientList clientList) {
        storage.saveClientList(clientList);
        return null;
    }
}
