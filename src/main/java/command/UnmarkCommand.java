package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

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
        if (isLineChanged(index, storeLine, storage)) {
            ui.showMessage("unmarked task");
        }
    }

    private boolean isLineChanged(int index, String storeLine, Storage storage) {
        return storage.isLineChanged(index, storeLine);
    }
}
