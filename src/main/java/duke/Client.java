package duke;

public class Client {
    private final String name;
    private int phoneNumber;
    private String address;

    public Client(String name, int phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean samePhoneNumber(int phoneNumber) {
        return this.phoneNumber == phoneNumber;
    }

    public void changePhoneNumber(int newNumber) {
        this.phoneNumber = newNumber;
    }

    public void changeAddress(String newAddress) {
        this.address = newAddress;
    }
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Address: " + address;
    }

    public String toSaveString() {
        return name + " " + phoneNumber + " " + address;
    }
}
