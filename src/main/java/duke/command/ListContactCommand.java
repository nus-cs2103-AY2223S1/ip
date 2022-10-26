package duke.command;

import duke.*;

import java.util.TreeMap;


public class ListContactCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) {
        TreeMap<String,Contact> contactList = contacts.getContactMap();
        String message = ui.showContactList(contactList);
        return message;
    }
}
