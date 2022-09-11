package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Abstract Command class for all commands.
 */
public abstract class Command {
    /** The response from Duke after the command is executed. */
    public String response = "";

    /**
     * Executes the command.
     *
     * @param taskList The tasklist that is a collection of tasks.
     * @param ui The ui that interacts with the user.
     * @throws DukeException If there are any errors that causes an exception to be thrown.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Returns the response from Duke after the command is executed.
     *
     * @return The response from Duke.
     */
    public String response() {
        return this.response;
    }

    /**
     * Returns a boolean to indicate if the command causes Duke to be exited.
     *
     * @return A boolean value.
     */
    public boolean isExit() {
        return false;
    }
}
