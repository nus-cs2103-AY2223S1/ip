package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The UndoCommand class represents the Undo command made by the user.
 */
public class UndoCommand extends Command {
    /**
     * Undoes changes made by the command to revert to original state.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the changes made have been undone.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        Command lastCommand = commandHistory.getLastCommand();
        if (lastCommand == null) {
            return ui.displayCommandMessage("There are no changes to be undone!", null, null);
        }
        return lastCommand.undoExecute(ui, storage, taskList, commandHistory);
    }

    /**
     * This command cannot be undone!
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        return "This is not supposed to be undone!";
    }
}
