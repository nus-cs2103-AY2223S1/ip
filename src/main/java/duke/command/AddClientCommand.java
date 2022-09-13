package duke.command;


import duke.Client;
import duke.ClientList;
import duke.DukeException;
import duke.task.TaskList;

public class AddClientCommand extends Command {

    private final Client client;

    /**
     * Constructs Add Client Command objects.
     *
     * @param name name of client.
     * @param phoneNumber phone number of client.
     * @param address address of client.
     */
    public AddClientCommand(String name, int phoneNumber, String address) throws DukeException {
        int length = String.valueOf(phoneNumber).length();
        if (length != 8) {
            throw new DukeException("Client phone number should be 8 numbers");
        }
        this.client = new Client(name, phoneNumber, address);
    }

    /**
     * Adds client to client list and saves it in save file.
     *
     * @param taskList list of tasks.
     * @param clientList list of clients.
     * @return String representation of how client list changed.
     */
    @Override
    public String execute(TaskList taskList, ClientList clientList) {
        clientList.add(client);
        SaveClientListCommand.of();
        return CommandOutputs.showNewClient(client, clientList);
    }
}
