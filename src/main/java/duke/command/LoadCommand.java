package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.FileIO;
import duke.Output;
import duke.StorageList;
import duke.Ui;

public class LoadCommand extends Command {
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        String filename = Duke.getDefaultFileName();
        String input = ui.getLastInput();
        if (input.split(" ").length == 2) {
            filename = input.split(" ")[1];
        }

        FileIO.load(storageList, filename);
        Output.LOAD.print();
    }
}
