package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Marks given task, and updates
 * status in TaskList and Storage.
 */
public class MarkCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        taskList.markTask(n);
        String storeLine = taskList.getTask(n).toString() + "\n";
        storage.changeLine(n, storeLine);
        ui.showMessage("marked task");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
