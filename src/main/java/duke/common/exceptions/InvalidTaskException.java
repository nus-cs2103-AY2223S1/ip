package duke.common.exceptions;

import duke.common.Messages;

/**
 * Represents an exception for trying to access tasks that do not exist.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructor for an InvalidTaskException.
     */
    public InvalidTaskException() {
        super(Messages.MESSAGE_NO_SUCH_TASK);
    }
}
