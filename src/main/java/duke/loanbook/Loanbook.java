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

    public Loanbook() {
        Loanbook.contacts = new ArrayList<>();
    }

    public Loanbook(ArrayList<Contact> contacts) {
        Loanbook.contacts = contacts;
    }

    /**
     * Adds a contact to the Loanbook.
     *
     * @param name The name of the new contact
     * @param phoneNumber Phone number of the new contact.
     * @param amount The loan between you and the contact.
     * @param isOwe True if you owe him, false otherwise.
     */
    public String addContact(String name, String phoneNumber, double amount, boolean isOwe) {
        for (Contact contact : Loanbook.contacts) {
            String contactName = contact.getName();

            if (contactName.equals(name)) {
                throw new DukeException("Sorry, this name already exist in the loanbook, try something else!");
            }
        }

        Contact newContact = new Contact(name, phoneNumber, amount, isOwe);

        Loanbook.contacts.add(newContact);

        return name;
    }

    /**
     * Deletes a contact from the Loanbook.
     *
     * @param nameToDelete The name to delete from the loan book.
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

    public ArrayList<Contact> getAllContacts() {
        return Loanbook.contacts;
    }

    @Override
    public String toString() {
        int index = 1;

        StringBuilder sb = new StringBuilder();
        for (Contact contact : Loanbook.contacts) {
            String contactRepresentation = index + ". " + contact + "\n";
            sb.append(contactRepresentation);
            index++;
        }

        return sb.toString();
    }
}
