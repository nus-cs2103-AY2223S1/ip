package yilia.command;

import yilia.exception.DescriptionEmptyException;
import yilia.exception.TimeFormatException;
import yilia.task.TaskList;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents an abstract command.
 */
public abstract class Command {
    private boolean isExit = false;
    public Command() {

    }
    public Command(boolean isExit) {
        this.isExit = isExit;
    }
    /**
     * Returns a boolean value to check if the command is an exit command.
     */
    public boolean isExit() {
        return isExit;
    }
    /**
     * Executes the command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     * @return A string indicating the execution status.
     * @throws DescriptionEmptyException  If the description of a task is empty.
     * @throws TimeFormatException  If the given time format is invalid.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws
            DescriptionEmptyException, TimeFormatException;
}
