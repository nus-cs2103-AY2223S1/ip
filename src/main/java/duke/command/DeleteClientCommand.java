package duke.command;

import duke.Client;
import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

/**
 * Represents a command that deletes a client from the client list.
 */
public class DeleteClientCommand extends Command {
    private final int phoneNumber;

    /**
     * Constructs Delete Client Command object.
     *
     * @param phoneNumber phone number of client.
     */
    public DeleteClientCommand(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Deletes client from client list and returns String representation of deleted client.
     *
     * @param taskList task list.
     * @param clientList client list.
     * @return String representation of deleted client.
     * @throws DukeException if no client has this phone number.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) throws DukeException {
        Client deletedClient = clientList.delete(phoneNumber);
        SaveClientListCommand.of().execute(taskList, clientList);
        return CommandOutputs.showDeletedClient(deletedClient);
    }
}
