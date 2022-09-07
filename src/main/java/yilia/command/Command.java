package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.TimeFormatException;
import yilia.task.TaskList;

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
     * @throws DescriptionEmptyException  If the description of a task is empty.
     * @throws TimeFormatException  If the given time format is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws
            DescriptionEmptyException, TimeFormatException;
}
