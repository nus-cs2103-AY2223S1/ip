package unc.command;

import unc.Storage;
import unc.TaskList;
import unc.Ui;
import unc.UncException;

/**
 * Command that finds tasks containing a keyword when executed.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String input) {
        this.keyword = input;
    }

    /**
     * Find the tasks in the list that contains the keyword.
     *
     * @param taskList List to be operated on.
     * @param ui UI to print message.
     * @param storage Storage to save updated list.
     * @return the message to be shown by UI
     * @throws UncException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws UncException {
        TaskList foundList = taskList.find(keyword);
        return ui.displayFoundList(foundList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
