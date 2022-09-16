package ip.command;

import ip.utility.Storage;
import ip.utility.TaskList;

/**
 * Duke command to revert tasklist to previous state.
 */
public class UndoCommand extends DukeCommand {

    /**
     * Reverts the current task list by 1 change.
     *
     * @param taskList Task list that the command acts on.
     * @param storage Storage that the command saves to/reads from.
     * @return Revert success message string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.set(storage.getBackupTaskList());
        storage.saveToLatest(taskList);
        return "Successfully reverted 1 change.";
    }
}
