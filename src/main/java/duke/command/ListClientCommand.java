package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public class ListClientCommand extends Command {

    private static final ListClientCommand LIST_CLIENT_COMMAND = new ListClientCommand();

    /**
     * Returns the list command.
     *
     * @return list command.
     */
    public static ListClientCommand of() {
        return LIST_CLIENT_COMMAND;
    }

    /**
     * Returns string representation of client list.
     *
     * @param taskList task list.
     * @param storage files storing task list.
     * @param clientList client list.
     * @return String representation of client list.
     * @throws DukeException if no clients.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        return CommandOutputs.showClientList(clientList);
    }
}
