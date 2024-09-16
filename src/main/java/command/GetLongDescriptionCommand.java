package command;

import static utility.Parser.isValidIndex;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;


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
        if (!isValidIndex(index, taskList.getSize())) {
            throw new DukeException("Invalid index");
        }
        ui.showMessage(taskList.getLongDescription(index));
    }
}
