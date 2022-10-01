package duke.command;

import duke.CustomMessageException;
import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Abstract class to represent a {@code Command}
 */
public abstract class Command {
    /**
     * If there are no preceding commands that have been run, this variable prevents the UNDO
     * command from running as there is nothing to undo.
     */
    protected static boolean isFirstUndoableCommandRun = true;

    /**
     * Executes the Command.
     * @param storage The {@code Storage} object
     * @param taskList The {@code TaskList} object
     * @return The output to display to the user
     * @throws CustomMessageException Exception to be thrown if the user passes invalid commands
     */
    public abstract String execute(Storage storage, TaskList taskList) throws CustomMessageException;

    /**
     * Saves the task list to storage after calling the {@code execute} of each individual {@code Command}.
     * @param storage The {@code Storage} object
     * @param taskList The {@code TaskList} object
     */
    public void saveTaskListToStorageAndUnlockUndo(Storage storage, TaskList taskList) {
        storage.writeToDisk(taskList.getTextRepresentationOfAllTasksForStorage());
        isFirstUndoableCommandRun = false;
    }
}
