package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;

/**
 * Command that exits the input loop when executed.
 */
public class ExitCommand extends Command {
    /**
     * Saves current list and prints exit message.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodbye();
    }

    /**
     * {@inheritDoc}
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
