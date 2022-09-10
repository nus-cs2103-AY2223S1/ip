package mort.command;

import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to find a task in the task list.
 */
public class FindCommand extends Command {
    /** Keyword to be searched */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getFindMessage(this.keyword, tasks.find(this.keyword));
    }
}
