package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * A class that encapsulate a ByeCommand, to
 * exit the ui
 */
public class ByeCommand extends Command {

    public ByeCommand(String args) {
        super(args);
    }

    /**
     * This will exit the program and do clean up.
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
