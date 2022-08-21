package duke.command;

import duke.Output;
import duke.StorageList;
import duke.Ui;

/**
 * Lists the tasks in the StorageList.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     *
     * @param ui the user interface
     * @param storageList the storage list
     */
    @Override
    public void execute(Ui ui, StorageList storageList) {
        Output.LIST.list(storageList);
    }
}
