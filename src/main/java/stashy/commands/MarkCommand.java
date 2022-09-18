package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to mark a particular task
 * in the task list as done.
 */
public class MarkCommand extends Command {
    public static final String KEYWORD = "mark";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nMarks the task referenced by the task ID as done."
        + "\n\nExample: mark 2";
    private int taskId;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param taskId Task ID from the task list
     * @param helpShown Whether to show help or not
     */
    private MarkCommand(Integer taskId, boolean helpShown) {
        this.taskId = taskId;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to mark a task as done.
     *
     * @param taskId Task ID from the task list
     */
    public MarkCommand(Integer taskId) {
        this(taskId, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public MarkCommand() {
        this(Integer.MIN_VALUE, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks a particular task from the given task list as done.
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
                returnString += "LFG, marking this task as done!";
                tasks.get(this.taskId - 1).markAsDone();
                returnString += "\n  " + tasks.get(this.taskId - 1);
                return returnString;
            } else {
                throw new StashyException("Invalid task ID: " + this.taskId);
            }
        }
    }
}
