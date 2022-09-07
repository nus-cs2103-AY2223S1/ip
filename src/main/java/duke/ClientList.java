package duke;

import java.util.ArrayList;

public class ClientList {
    private final ArrayList<Client> clientList;

    public ClientList() {
        this.clientList = new ArrayList<>();
    }

    public void add(Client newClient) {
        clientList.add(newClient);
    }

    public int size() {
        return clientList.size();
    }

    public Client delete(int phoneNumber) throws DukeException {
        for(Client client: clientList) {
            if(client.samePhoneNumber(phoneNumber)) {
                clientList.remove(client);
                return client;
            }
        }
        throw new DukeException("Client with this phone number does not exist");
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(Client client: clientList) {
            output.append(client.toString()).append("\n");
        }
        return output.toString();
    }
}
