package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;
import gina.Contact;

import java.util.regex.PatternSyntaxException;

public class ContactCommand extends Command {
    private String input;

    /**
     * Constructs command to create a new contact with the specified input.
     *
     * @param input The user input for contact information.
     */
    public ContactCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        if (input.isBlank()) {
            throw new GinaException("Gina knows best but she can't read minds...");
        }
        Contact newContact;
        try {
            String[] str = input.split(" /info ", 2);
            newContact = new Contact(str[0], str[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            newContact = new Contact(input, "");
        }

        taskAndContactList.addContact(newContact);
        storage.save(taskAndContactList);
        return ui.showAddContact(newContact);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
