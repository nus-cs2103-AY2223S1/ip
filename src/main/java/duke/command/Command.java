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
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
