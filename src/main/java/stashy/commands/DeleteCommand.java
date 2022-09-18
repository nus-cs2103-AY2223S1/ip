package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to delete a particular Task
 * from the task list.
 */
public class DeleteCommand extends Command {
    public static final String KEYWORD = "delete";
    public static final String HELP_MESSAGE = KEYWORD
        + "\n\nDeletes a task based on the task ID provided."
        + "\n\nExample: delete 3";
    private int taskId;
    private boolean helpShown;

    /**
     * Represents the constructor method.
     *
     * @param taskId Task ID from the task list
     * @param helpShown Whether to show help or not
     */
    private DeleteCommand(Integer taskId, boolean helpShown) {
        this.taskId = taskId;
        this.helpShown = helpShown;
    }

    /**
     * Overloads the constructor method to delete a task.
     *
     * @param taskId Task ID from the task list
     */
    public DeleteCommand(Integer taskId) {
        this(taskId, false);
    }

    /**
     * Overloads the constructor method to show help.
     */
    public DeleteCommand() {
        this(Integer.MIN_VALUE, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes a particular task from the given task list.
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
                returnString += "Task has been removed!\n  " + tasks.get(this.taskId - 1);
                tasks.remove(this.taskId - 1);
                returnString += "\nYou have " + tasks.size() + " task(s) in the list.";
                return returnString;
            } else {
                throw new StashyException("Invalid task ID: " + this.taskId);
            }
        }
    }
}
