package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * An abstract class for commands.
 */
public abstract class Command {

    /** The text response after the command has been executed to be displayed. */
    String response;

    /**
     * Executes the respective command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws DukeException If there are any errors that cause exceptions to be thrown.
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * Returns the response after the command has been executed.
     *
     * @return The response after the command has been executed.
     */
    public String response() {
        return this.response;
    }

    /**
     * Returns true if the command is "bye", and false otherwise.
     *
     * @return True if the command is "bye", and false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
