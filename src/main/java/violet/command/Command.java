package violet.command;

import violet.TaskList;
import violet.Ui;
import violet.exception.VioletException;

/**
 * Abstract Command class for all commands.
 */
public abstract class Command {
    /** The response from Violet after the command is executed. */
    public String response = "";

    /**
     * Executes the command.
     *
     * @param taskList The tasklist that is a collection of tasks.
     * @param ui The ui that interacts with the user.
     * @throws VioletException If there are any errors that causes an exception to be thrown.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws VioletException;

    /**
     * Returns the response from Violet after the command is executed.
     *
     * @return The response from Duke.
     */
    public String response() {
        return this.response;
    }

    /**
     * Returns a boolean to indicate if the command causes Violet to be exited.
     *
     * @return A boolean value.
     */
    public boolean isExit() {
        return false;
    }
}
