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
        String command = ui.getCurrentInput();
        boolean isExitCommand = command.trim().equalsIgnoreCase("bye");
        if (isExitCommand) {
            ui.showExitMessage();
            System.exit(0);
        } else {
            ui.showError("error parsing exit command");
        }
    }
}
