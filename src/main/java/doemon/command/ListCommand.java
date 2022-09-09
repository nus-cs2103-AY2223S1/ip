package doemon.command;

import doemon.storage.Storage;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Command to list all tasks currently recorded.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
