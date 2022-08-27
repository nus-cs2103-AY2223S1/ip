package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for <code>FindCommand</code>.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Show the tasks containing the given keyword.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matches = tasks.findTasks(keyword);
        ui.showTaskList(matches);
    }

    /**
     * Check if the command exit duke.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
