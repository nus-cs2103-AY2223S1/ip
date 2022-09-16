package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructs a command to mark a specified task as done.
     *
     * @param input The user input for task number.
     */
    public MarkCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskAndContactList.getTask(index).markAsDone();
            storage.save(taskAndContactList);
            return ui.showMarkedTask(taskAndContactList.getTask(index));
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
