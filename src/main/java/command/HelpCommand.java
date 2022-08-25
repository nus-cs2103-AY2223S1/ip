package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;


public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
