package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;
import utility.Parser;

/**
 * Unmarks given task, and updates
 * status in TaskList and Storage.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.unmarkTask(index);
        String storeLine = taskList.getTask(index).toString() + "\n";
        if (storage.isLineChanged(index, storeLine)) {
            ui.showMessage("unmarked task");
        }
    }
}
