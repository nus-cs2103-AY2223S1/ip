package duke;

import java.util.ArrayList;

/**
 * Represents the client list of the user.
 */
public class ClientList {
    private final ArrayList<Client> clientList = new ArrayList<>();

    public void add(Client newClient) {
        clientList.add(newClient);
    }

    public int size() {
        return clientList.size();
    }

    /**
     * Deletes client from client list and returns deleted client.
     *
     * @param phoneNumber phone number of client to be deleted.
     * @return deleted client.
     * @throws DukeException if no client has the phone number.
     */
    public Client delete(int phoneNumber) throws DukeException {
        for (Client client: clientList) {
            if (client.samePhoneNumber(phoneNumber)) {
                clientList.remove(client);
                return client;
            }
        }
        throw new DukeException("Client with this phone number does not exist");
    }

    /**
     * Returns String representation of client list.
     *
     * @return String representation of client list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Client client: clientList) {
            output.append(client.toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns String representation of client list for save file.
     *
     * @return String representation of client list for save file.
     */
    public String toSaveString() {
        StringBuilder output = new StringBuilder();
        for (Client client: clientList) {
            output.append(client.toSaveString()).append("\n");
        }
        return output.toString();
    }
}
