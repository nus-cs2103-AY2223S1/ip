package duke.command;

import duke.*;
import duke.task.TaskList;

public class DeleteClientCommand extends Command {
    int phoneNumber;

    /**
     * Constructor for Delete Client Command.
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
     * @param storage files storing task list.
     * @param clientList client list.
     * @return String representation of deleted client.
     * @throws DukeException if no client has this phone number.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        Client deletedClient = clientList.delete(phoneNumber);
        new SaveClientListCommand().execute(taskList, storage, clientList);
        return CommandOutputs.showDeletedClient(deletedClient);
    }
}
