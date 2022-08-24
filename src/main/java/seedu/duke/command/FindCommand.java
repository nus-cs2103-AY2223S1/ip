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
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayMatchingTasks();
        tasks.displayMatchingTasks(keyword);
    }
}
