package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to unmark a particular task
 * in the task list as not done.
 */
public class UnmarkCommand extends Command {
    public static final String KEYWORD = "unmark";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nUnmarks the task referenced by task ID as not done."
        + "\n\nExample: unmark 4";
    private int taskId;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param taskId Task ID from the task list
     * @param helpShown Whether to show help or not
     */
    private UnmarkCommand(Integer taskId, boolean helpShown) {
        this.taskId = taskId;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to unmark a task as not done.
     *
     * @param taskId Task ID from the task list
     */
    public UnmarkCommand(Integer taskId) {
        this(taskId, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public UnmarkCommand() {
        this(Integer.MIN_VALUE, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Unmarks a particular task from the given task list as not done.
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
            if (1 <= this.taskId && this.taskId <= tasks.size()) {
                String returnString = "";
                returnString += "L + ratio, unmarking this task as not done!";
                tasks.get(this.taskId - 1).unmarkAsNotDone();
                returnString += "\n  " + tasks.get(this.taskId - 1);
                return returnString;
            } else {
                throw new StashyException("Invalid task ID: " + this.taskId);
            }
        }
    }
}
