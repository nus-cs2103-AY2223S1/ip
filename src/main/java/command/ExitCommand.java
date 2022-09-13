package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Causes program to terminate.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showExitMessage();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
