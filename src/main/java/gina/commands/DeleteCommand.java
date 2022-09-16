package gina.commands;

import gina.*;
import gina.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final String input;

    /**
     * Constructs a command to delete a specified task.
     *
     * @param input The user input for the index of a task.
     */
    public DeleteCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        try {
            String c = input.trim().substring(0, 1);
            int index = Integer.parseInt(input.trim().substring(1)) - 1;
            if (c.equals("t")) {
                Task deletedTask = taskAndContactList.deleteTask(index);
                storage.save(taskAndContactList);
                return ui.showDeleteTask(deletedTask, taskAndContactList);
            } else if (c.equals("c")) {
                Contact deletedContact = taskAndContactList.deleteContact(index);
                storage.save(taskAndContactList);
                return ui.showDeleteContact(deletedContact);
            } else {
                throw new GinaException("Is it a contact (e.g C1) or task(e.g T2)??");
            }
        } catch (NumberFormatException e) {
            throw new GinaException("Hey! Input a valid number!");
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
