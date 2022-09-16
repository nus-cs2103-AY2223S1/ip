package gina.commands;

import gina.GinaException;
import gina.Storage;
import gina.TaskAndContactList;
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
        assert(input != null);
        this.input = input.trim();
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskAndContactList taskAndContactList, Ui ui, Storage storage) throws GinaException {
        TaskAndContactList tasksOnDate = taskAndContactList.getTasksOnDate(input);
        return ui.showTasksOnDate(tasksOnDate, input);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
