package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
import gina.Ui;

/**
 * Represents the command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructs command for marking a task as not done.
     *
     * @param input The index of the task.
     */
    public UnmarkCommand(String input) {
        this.input = input;
        assert(input != null);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskAndContactList.getTask(index).markAsUndone();
            storage.save(taskAndContactList);
            return ui.showUnmarkedTask(taskAndContactList.getTask(index));
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
