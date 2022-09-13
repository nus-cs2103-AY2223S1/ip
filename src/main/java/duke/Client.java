package duke;

/**
 * Represents a client of the user.
 */
public class Client {
    private final String name;
    private final int phoneNumber;
    private final String address;

    /**
     * Constructs a client object.
     *
     * @param name name of client.
     * @param phoneNumber phone number of client.
     * @param address address of client.
     */
    public Client(String name, int phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean samePhoneNumber(int phoneNumber) {
        return this.phoneNumber == phoneNumber;
    }

    /**
     * Returns String representation of client.
     *
     * @return String representation of client.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Address: " + address;
    }

    /**
     * Returns String representation of client in save file format.
     *
     * @return String representation of client in save file format.
     */
    public String toSaveString() {
        return name + " " + phoneNumber + " " + address;
    }
}
