package duke.parser.exceptions;

import duke.exception.DukeException;

/**
 * Represents an exception for invalid user commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("Sorry! I don't know what that means :(");
    }
}
