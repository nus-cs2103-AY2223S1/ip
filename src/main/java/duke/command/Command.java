package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This abstract class provides the skeletal implementation of a user command
 * and should be the superclass of all user commands.
 */
public abstract class Command {

    /**
     * Executes this Command with the specified TaskList, Ui, and Storage parameters.
     *
     * @param tasks the specified TaskList object.
     * @param ui the specified Ui object.
     * @param storage the specified Storage object.
     * @throws DukeException if an Exception occurs during its execution.
     */
    public abstract void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if and only if this Command is a terminating Command, i.e.
     * after executing the command, the application should exit cleanly.
     *
     * @return true if and only if this Command is a terminating Command.
     */
    public abstract boolean isTerminator();
}
