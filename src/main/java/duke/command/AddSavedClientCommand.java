package duke.command;

import duke.*;
import duke.task.TaskList;

public class AddSavedClientCommand extends Command {

    private final Client client;

    public AddSavedClientCommand(String name, int phoneNumber, String address) {
        this.client = new Client(name, phoneNumber, address);
    }

    /**
     * Adds client to client list.
     *
     * @param taskList list of tasks.
     * @param storage files storing task list.
     * @param clientList list of clients.
     * @return nothing.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) {
        clientList.add(client);
        return null;
    }
}
