package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Parser;
import duke.StorageList;
import duke.Ui;

/**
 * Marks the specified Task in the StorageList.
 */
public class MarkCommand extends Command {
    /**
     * Executes the mark command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        int index = Parser.getIndex(ui.getLastInput());
        storageList.mark(index);
        Output.MARK.changeStatus(storageList.get(index));
    }
}
