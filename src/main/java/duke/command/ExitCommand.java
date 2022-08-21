package duke.command;

import duke.Output;
import duke.StorageList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, StorageList storageList) {
        Output.GOODBYE.print();
        isExit = true;
    }
}
