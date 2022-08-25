package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Returns long description of task.
 */
public class GetLongDescriptionCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        ui.showMessage(taskList.getLongDescription(n));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
