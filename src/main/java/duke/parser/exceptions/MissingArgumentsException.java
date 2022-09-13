package duke.parser.exceptions;

import duke.exception.DukeException;

/**
 * Represents an exception for missing command arguments.
 */
public class MissingArgumentsException extends DukeException {
    /**
     * Constructor for a MissingArgumentsException.
     * @param message The error message to inform the user that more command arguments are needed.
     */
    public MissingArgumentsException(String message) {
        super(message);
    }
}
