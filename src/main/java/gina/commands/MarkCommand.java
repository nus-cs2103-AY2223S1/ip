package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskList;
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GinaException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            taskList.get(index).markAsDone();
            storage.save(taskList);
            return ui.showMarkedTask(taskList.get(index));
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
