package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * A command to exit Byu.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
