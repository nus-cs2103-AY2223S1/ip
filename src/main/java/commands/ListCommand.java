package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Prints the current tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Lists the current tasks.
     * <p>
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCurrentTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
