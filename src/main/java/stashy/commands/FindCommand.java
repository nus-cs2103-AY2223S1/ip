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
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nLists all the tasks filtered by a specific string query."
        + "\n\nExample: find book";

    /**
     * The query string that filters the task list upon command execution.
     */
    private String query;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param query The query string of interest
     * @param helpShown Whether to show help or not
     */
    private FindCommand(String query, boolean helpShown) {
        this.query = query;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to search filtered tasks.
     *
     * @param query The query string of interest
     */
    public FindCommand(String query) {
        this(query, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public FindCommand() {
        this(null, true);
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
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.helpShown) {
            return HELP_MESSAGE;
        } else {
            return ui.showFilteredTasksString(new TaskList(tasks.getArrayList(), this.query));
        }
    }
}
