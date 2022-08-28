package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;

/**
 * Command that displays the current list when executed.
 */
public class ListCommand extends Command {
    /**
     * Displays the list.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayList(taskList);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
