package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Deletes task.
 */
public class DeleteTaskCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        taskList.deleteTask(n);
        storage.deleteLine(n);
        ui.showMessage("Deleted task");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
