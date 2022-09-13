package duke.command;

import duke.*;
import duke.Contact;

public class DeleteContactCommand extends Command {
    private String name;

    public DeleteContactCommand(String name) {
        this.name = name;
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) throws DukeException {
        try {
            contacts.deleteContact(name);
            int total = contacts.totalSize();
            String message = ui.showDeleteContact(name, total);
            container.store(contacts.getContactMap());
            return message;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
