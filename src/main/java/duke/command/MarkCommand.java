package duke.command;

import duke.DukeException;
import duke.Output;
import duke.Parser;
import duke.StorageList;
import duke.Ui;

public class MarkCommand extends Command {
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        int index = Parser.getIndex(ui.getLastInput());
        storageList.mark(index);
        Output.MARK.changeStatus(storageList.get(index));
    }

}
