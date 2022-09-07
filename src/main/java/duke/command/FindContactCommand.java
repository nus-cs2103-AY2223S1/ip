package duke.command;

import duke.*;




public class FindContactCommand extends Command {
    private String name;

    public FindContactCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Container container, ContactMap contacts) {
        try {
            Contact contact = contacts.findContact(name);
            String message = ui.showFindContact(contact);
            return message;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
