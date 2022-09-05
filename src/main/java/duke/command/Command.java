package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Abstract class to encapsulate Duke Commands.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks TaskList of Duke
     * @param ui Ui of Duke
     * @param storage Storage of Duke
     * @throws DukeException if error occurs during runtime
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if Duke should exit after executing this command.
     * @return true if Duke should exit, false otherwise
     */
    public abstract boolean isExit();
}
