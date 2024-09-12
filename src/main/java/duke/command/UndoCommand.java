package duke.command;

import duke.Storage;
import duke.StoreUndo;
import duke.TaskList;
import duke.Ui;

/**
 * A command that undoes the previous task-changing command.
 */
public class UndoCommand extends Command {
    private Command undo;

    /**
     * Constructor for UndoCommand.
     * @param undo Command that undoes the previous task-changing command.
     */
    public UndoCommand(Command undo) {
        this.undo = undo;
    }

    /**
     * Undoes the previous task-changing command and returns the toString of the undo Command.
     * @param list TaskList containing the list of tasks.
     * @param storage Storage that loads and saves to the save file.
     * @return toString of the undo Command.
     */
    @Override
    public String execute(TaskList list, Storage storage) {
        if (undo instanceof ListCommand) {
            Ui.noUndoMessage();
        } else {
            Ui.undoMessage();
        }
        StoreUndo.updateUndo(new ListCommand());
        return this.undo.execute(list, storage);
    }
}
