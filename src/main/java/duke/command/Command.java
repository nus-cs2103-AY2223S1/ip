package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Super class of all Commands.
 */
public abstract class Command {
    /**
     * Returns whether the Command is the Exit command.
     *
     * @return Whether the Command is the Exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Runs instructions of command.
     *
     * @param tasks List of tasks.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response.
     * @throws DukeException
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
