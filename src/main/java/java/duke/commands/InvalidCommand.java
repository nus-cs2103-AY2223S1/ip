package duke.commands;

import duke.exceptions.DukeException;

/**
 * Handles the Invalid commands
 */
public class InvalidCommand extends DisplayCommand {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Throws invalid command error
     *
     * @return the wrapped message
     * @throws DukeException when the message is wrong
     */
    public String execute() throws DukeException {
        return wrapWithoutLines(message);
    }
}
