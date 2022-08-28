package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Super class of all Commands.
 */
public abstract class Command {
    /**
     * Returns whether the Command is the Exit command.
     * @return Whether the Command is the Exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Runs instructions of command.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
