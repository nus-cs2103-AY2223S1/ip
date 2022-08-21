package duke.command;

import duke.Output;
import duke.StorageList;
import duke.Ui;

/**
 * Exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     */
    @Override
    public void execute(Ui ui, StorageList storageList) {
        Output.GOODBYE.print();
        isExit = true;
    }
}
