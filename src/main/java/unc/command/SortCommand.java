package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

/**
 * Command that sorts the list when executed.
 */
public class SortCommand extends Command {

    /**
     * Sorts list of tasks by their deadline or time of event.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @return the message to be shown by UI
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.sort();
        storage.save(taskList);
        return ui.displayList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
