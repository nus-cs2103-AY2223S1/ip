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
    private String[] slicedUserCommands;

    public DeleteTaskCommand(String[] slicedUserInput) {
        this.slicedUserCommands = slicedUserInput;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        int n = Parser.getTaskNumber(ui.getCurrentInput(), taskList.getSize());
        taskList.deleteTask(n);
        if (storage.isLineDeleted(n)) {
            ui.showMessage("Deleted task");
        }
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
