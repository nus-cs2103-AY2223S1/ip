package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList tasksOnDate = taskList.getTasksOnDate(input);
        ui.showTasksOnDate(tasksOnDate, input);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
