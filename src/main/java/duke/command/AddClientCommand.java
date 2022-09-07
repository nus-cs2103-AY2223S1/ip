package duke.command;

import duke.*;

public class AddClientCommand extends Command {

    private Client client;

    public AddClientCommand(String name, int phoneNumber, String address) {
        this.client = new Client(name, phoneNumber, address);
    }

    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList task list.
     * @param commandOutputs user interface of program.
     * @param storage files storing task list.
     * @param clientList
     * @return String response of Duke regarding user input
     * @throws DukeException if error occurs during execution of command.
     */
    @Override
    public String execute(TaskList taskList, CommandOutputs commandOutputs, Storage storage, ClientList clientList) throws DukeException {
        clientList.add(client);
        return commandOutputs.showNewClient(client, clientList);
    }
}
