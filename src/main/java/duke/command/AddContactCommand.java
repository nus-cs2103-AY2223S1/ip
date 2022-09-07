package duke.command;

import duke.*;
import duke.Contact;

public class AddContactCommand extends Command {
    private Contact contact;

    public AddContactCommand(Contact contact) {
        this.contact = contact;
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) throws DukeException {
        try {
            contacts.addContact(contact);
            int total = contacts.totalSize();
            String message = ui.showAddContact(contact, total);
            container.store(contacts.getContactMap());
            return message;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
