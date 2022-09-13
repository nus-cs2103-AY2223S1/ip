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
    private int index;

    public GetLongDescriptionCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.showMessage(taskList.getLongDescription(index));
    }
}
