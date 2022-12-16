package commands;

import exception.FredException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Find command for finding all tasks containing given keyword.
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Create FindCommand
     * @param keyword keyword to be looked for in all tasks
     */
    public FindCommand(String keyword) {
        isExit = false;
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FredException {
        ui.storeMessage(tasks.find(keyword));
    }
}
