package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.storage.exceptions.StorageException;
import duke.ui.Ui;

/**
 * Represents the command to undo the most recent command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    /**
     * Undos the most recent command.
     * @param taskList List of tasks.
     * @param ui Shows the message that the most recent command has been undone.
     * @param storage Saves the modified list of tasks.
     * @return The message indicating that the most recent change to the task list has been undone.
     * @throws StorageException If there is an error saving the modified list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws StorageException {
        storage.undo(taskList);
        return ui.showUndo(taskList);
    }
}
