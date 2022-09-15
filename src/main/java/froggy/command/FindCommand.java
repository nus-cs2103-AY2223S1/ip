package froggy.command;

import froggy.ui.Ui;
import froggy.storage.Storage;
import froggy.task.TaskList;

/**
 * A FindCommand class which extends the Command class.
 */
public class FindCommand extends Command {
    /* The keyword to search the tasks for */
    protected String keyword;

    /**
     * Creates a FindCommand object.
     *
     * @param keyword The keyword to search for in the tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Displays the matching tasks based on this.keyword.
     *
     * @param tasks The TaskList object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String messageToUser = ui.displayMatchingTasks() + tasks.displayMatchingTasks(keyword);
        return messageToUser;
    }
}
