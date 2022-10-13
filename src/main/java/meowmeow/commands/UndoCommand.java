package meowmeow.commands;

import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class UndoCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used to undo the user's last command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class UndoCommand extends Command {

    /**
     * Executes the UndoCommand.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to undo the last command.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.undo();
    }

    /**
     * Returns false for UndoCommand.
     * @return false for UndoCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
