package commands;

import dukeegg.Storage;
import dukeegg.TaskList;
import ui.Ui;

/**
 * Prints the current tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * Lists the current tasks.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showCurrentTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
