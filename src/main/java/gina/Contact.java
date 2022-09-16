package gina;

/**
 * Represents a contact.
 */
public class Contact {
    protected String name;
    protected String description;

    /**
     * Constructs an object representing a contact with the relevant details.
     *
     * @param name The name of the contact.
     * @param description Additional information about the contact.
     */
    public Contact(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns a contact from a string representation of a contact.
     *
     * @param input The string representation of the contact.
     * @return The contact.
     */
    public static Contact createContactFromString(String input) {
        String[] str = input.substring(3).split("\\(info: ", 2);
        return new Contact(str[0], str[1].substring(0, str[1].length() - 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name + " (info: " + description + ")";
    }
}
