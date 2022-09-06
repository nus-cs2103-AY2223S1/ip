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

    private String actualCommandUsedToInvoke;

    public GetLongDescriptionCommand(String actualCommandUsed) {
        this.actualCommandUsedToInvoke = actualCommandUsed;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        ui.showMessage(taskList.getLongDescription(n));
    }
}
