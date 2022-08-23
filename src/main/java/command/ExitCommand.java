package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String command = ui.currentInput;
        if (command.trim().equalsIgnoreCase("bye")) {
            ui.showMessage("Thank you for using Falcon.");
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
