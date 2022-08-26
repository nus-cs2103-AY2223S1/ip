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
     * @throws DukeException when the message is wrong
     */
    public void execute() throws DukeException {
        wrapWithLines(message);
    }
}
