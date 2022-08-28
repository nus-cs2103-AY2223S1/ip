package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFindMessage(this.keyword, tasks.find(this.keyword));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
