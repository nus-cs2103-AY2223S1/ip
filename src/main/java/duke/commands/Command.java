package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
     * @param ui Ui to interact with the user.
     * @param storage Storage to save and load tasks from a local file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
