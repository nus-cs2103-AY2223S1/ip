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
     * Runs command that will do various changes based on the command.
     *
     * @param taskList       task list.
     * @param storage        files storing task list.
     * @param clientList
     * @return String response of Duke regarding user input
     * @throws DukeException if error occurs during execution of command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        return CommandOutputs.showClientList(clientList);
    }
}
