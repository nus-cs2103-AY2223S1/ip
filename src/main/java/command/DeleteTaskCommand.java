package command;

import exceptions.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

/**
 * Deletes task.
 */
public class DeleteTaskCommand extends Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        if (isLineDeleted(index, storage)) {
            taskList.deleteTask(index);
            ui.showMessage("Deleted task");
        }
    }

    private boolean isLineDeleted(int index, Storage storage) {
        return storage.isLineDeleted(index);
    }

    /**
     * Returns true to cause program termination.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
