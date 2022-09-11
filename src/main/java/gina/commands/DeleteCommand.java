package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.task.Task;
import gina.TaskList;
import gina.Ui;

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
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GinaException {
        try {
            int index = Integer.parseInt(input.trim()) - 1;
            Task deletedTask = taskList.deleteTask(index);
            storage.save(taskList);
            return ui.showDeleteTask(deletedTask, taskList);
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
