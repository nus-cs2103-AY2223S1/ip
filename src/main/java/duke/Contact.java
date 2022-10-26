package duke;

public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name,String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String parse() {
        return this.name + "#" + this.phoneNumber;
    }

    public String toString() {
        return this.name + ": " + this.phoneNumber + " (hp)";
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }


}
