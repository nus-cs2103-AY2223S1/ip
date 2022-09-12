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
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nLists all the tasks that you currently have."
        + "\n\nExample: list";
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param helpShown Whether to show help or not
     */
    public ListCommand(boolean helpShown) {
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to show help.
     */
    public ListCommand() {
        this(true);
    }

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
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (this.helpShown) {
            return HELP_MESSAGE;
        } else {
            return ui.showTasksString(tasks);
        }
    }
}
