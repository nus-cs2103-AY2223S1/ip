package doemon.command;

import doemon.storage.Storage;
import doemon.task.TaskList;
import doemon.ui.Ui;

/**
 * Command to exit the Doemon chat bot.
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
