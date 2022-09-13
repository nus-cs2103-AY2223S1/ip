package duke.command;

import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

public class ListClientCommand extends Command {

    private static final ListClientCommand LIST_CLIENT_COMMAND = new ListClientCommand();

    /**
     * Returns the list client command.
     *
     * @return list client command.
     */
    public static ListClientCommand of() {
        return LIST_CLIENT_COMMAND;
    }

    /**
     * Returns string representation of client list.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return String representation of client list.
     * @throws DukeException if no clients.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        return CommandOutputs.showClientList(clientList);
    }
}
