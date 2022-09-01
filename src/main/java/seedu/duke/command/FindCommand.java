package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

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
     * @param tasks The tasks object containing all the tasks and CRUD methods to modify the tasks.
     * @param ui The Ui object capable of displaying user interface.
     * @param storage The storage object capable of doing write, load, open functionality.
     * @return the reply from the bot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return ui.displayMatchingTasks() + tasks.displayMatchingTasks(keyword);
    }
}
