package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.StoreUndo;
import duke.Ui;

public class UndoCommand extends Command {
    private Command undo;

    public UndoCommand(Command undo) {
        this.undo = undo;
    }

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
