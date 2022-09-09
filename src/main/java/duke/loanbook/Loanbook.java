package duke.loanbook;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Keeps track of contacts in the Loanbook.
 *
 * @author Elgin
 */
public class Loanbook {
    /** All contacts in the Loanbook. */
    private static ArrayList<Contact> contacts;

    /**
     * Constructor of Loanbook.
     *
     */
    public Loanbook() {
        Loanbook.contacts = new ArrayList<Contact>();
    }

    /**
     * Adds a contact to the Loanbook.
     *
     */
    public String addContact(String name, String phoneNumber, double amount, boolean isOwe) {
        Contact newContact = new Contact(name, phoneNumber, amount, isOwe);

        Loanbook.contacts.add(newContact);

        return name;
    }

    /**
     * Gets String representation of all contact in the loanbook.
     *
     */
    public String getContacts() {
        int index = 1;

        StringBuilder sb = new StringBuilder();
        for (Contact contact : Loanbook.contacts) {
            String contactRepresentation = index + ". " + contact + "\n";
            sb.append(contactRepresentation);
            index++;
        }

        return sb.toString();
    }

    /**
     * Deletes a contact from the Loanbook.
     *
     */
    public String deleteContact(String nameToDelete) {
        boolean isRemoved = false;
        for (Contact contact : contacts) {
            String contactName = contact.getName();

            if (contactName.equals(nameToDelete)) {
                contacts.remove(contact);
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            throw new DukeException("No such name in the Loanbook! Delete operation aborted...");
        }

        return nameToDelete;
    }
}
