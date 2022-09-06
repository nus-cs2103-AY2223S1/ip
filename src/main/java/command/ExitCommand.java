package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Causes program to terminate.
 */
public class ExitCommand extends Command {
    private String actualCommandUsedToInvoke;

    public ExitCommand(String actualCommandUsed) {
        this.actualCommandUsedToInvoke = actualCommandUsed;
    }
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

    @Override
    public boolean isExit() {
        return true;
    }
}
