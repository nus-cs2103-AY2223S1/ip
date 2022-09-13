package duke.command;

import duke.ClientList;
import duke.Storage;
import duke.task.TaskList;

public class SaveClientListCommand extends Command {
    private static final SaveClientListCommand SAVE_CLIENT_LIST_COMMAND = new SaveClientListCommand();

    /**
     * Returns the save client list command.
     *
     * @return save client list command.
     */
    public static SaveClientListCommand of() {
        return SAVE_CLIENT_LIST_COMMAND;
    }

    /**
     * Saves tasks in task list to save file.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return nothing.
     */
    public String execute(TaskList taskList, ClientList clientList) {
        Storage.saveClientList(clientList);
        return null;
    }
}
