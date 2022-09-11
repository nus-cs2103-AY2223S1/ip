package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskList;
import gina.Ui;

/**
 * Represents a command to return the list of tasks on the specified date.
 */
public class OnCommand extends Command {
    private String input;

    /**
     * Constructs command to return the list of tasks with a specified input date.
     *
     * @param input The user input for date.
     */
    public OnCommand(String input) {
        this.input = input.trim();
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws GinaException {
        TaskList tasksOnDate = taskList.getTasksOnDate(input);
        return ui.showTasksOnDate(tasksOnDate, input);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
