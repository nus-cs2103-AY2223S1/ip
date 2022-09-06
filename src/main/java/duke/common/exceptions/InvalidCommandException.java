package duke.common.exceptions;

import duke.common.Messages;

/**
 * Represents an exception for invalid user commands.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for an InvalidCommandException.
     */
    public InvalidCommandException() {
        super(Messages.MESSAGE_INVALID_COMMAND);
    }
}
