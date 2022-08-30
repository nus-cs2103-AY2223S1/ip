package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to show all the tasks
 * from the task list.
 */
public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Shows all the tasks from the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showTasks(tasks);
    }
}
