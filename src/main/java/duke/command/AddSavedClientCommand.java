package duke.command;

import duke.*;
import duke.task.TaskList;

public class AddSavedClientCommand extends Command {

    private final Client client;

    /**
     * Constructs Add Saved Client Command object.
     *
     * @param name name of client.
     * @param phoneNumber phone number of client.
     * @param address address of client.
     */
    public AddSavedClientCommand(String name, int phoneNumber, String address) {
        this.client = new Client(name, phoneNumber, address);
    }

    /**
     * Adds client to client list.
     *
     * @param taskList list of tasks.
     * @param clientList list of clients.
     * @return nothing.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) {
        clientList.add(client);
        return null;
    }
}
