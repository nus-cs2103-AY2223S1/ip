package duke.commands;

import duke.exceptions.DukeException;

/**
 * This class encapsulates a set of instructions to be performed by Duke.
 */
public interface Command {

    /**
     * Executes the command from the user.
     *
     * @return The string to be shown by Duke on the dialogue box.
     * @throws DukeException When there is exception during the execution of the command.
     */
    String execute() throws DukeException;
}
