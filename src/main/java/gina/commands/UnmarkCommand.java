package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskList;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GinaException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskList.get(index).markAsUndone();
            storage.save(taskList);
            return ui.showUnmarkedTask(taskList.get(index));
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
