package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.FileIO;
import duke.Output;
import duke.StorageList;
import duke.Ui;

/**
 * Saves the StorageList to a file.
 */
public class SaveCommand extends Command {
    /**
     * Executes the save command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     * @throws DukeException if the command is invalid
     */
    @Override
    public void execute(Ui ui, StorageList storageList) throws DukeException {
        String filename = Duke.getDefaultFileName();
        String input = ui.getLastInput();
        if (input.split(" ").length == 2) {
            filename = input.split(" ")[1];
        }

        FileIO.save(storageList, filename);
        Output.SAVE.print();
    }
}
