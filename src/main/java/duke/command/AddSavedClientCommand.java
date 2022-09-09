package duke.command;

import duke.*;
import duke.task.TaskList;

public class AddSavedClientCommand extends Command {

    private final Client client;

    public AddSavedClientCommand(String name, int phoneNumber, String address) {
        this.client = new Client(name, phoneNumber, address);
    }

    /**
     * Runs command that will do various changes based on the command.
     *
     * @param taskList task list.
     * @param storage files storing task list.
     * @param clientList
     * @return String response of Duke regarding user input
     * @throws DukeException if error occurs during execution of command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, ClientList clientList) throws DukeException {
        clientList.add(client);
        return null;
    }
}
