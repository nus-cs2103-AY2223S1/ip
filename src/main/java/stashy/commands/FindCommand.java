package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to find all tasks
 * from the task list given a query string.
 */
public class FindCommand extends Command {
    public static final String KEYWORD = "find";

    /**
     * The query string that filters the task list upon command execution.
     */
    private String query;

    /**
     * Constructor method.
     *
     * @param query The query string of interest
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Finds all tasks from the given task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showFilteredTasks(new TaskList(tasks.getArrayList(), this.query));
    }
}
