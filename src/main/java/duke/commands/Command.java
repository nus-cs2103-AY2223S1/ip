package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command that a user would send to Duke.
 */
public abstract class Command {
    private boolean isExit = false;

    /**
     * Sets the value of isExit to true.
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Returns the value of isExit to indicate to Duke
     * if this command is an exit command or not.
     *
     * @return Value of isExit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param tasks Contains the task list.
     * @param storage Storage to save and load tasks from a local file.
     * @return The response of Duke.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
