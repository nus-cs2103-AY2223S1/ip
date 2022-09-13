package duke.data.exceptions;

import duke.exception.DukeException;

/**
 * Represents an exception for trying to access tasks that do not exist.
 */
public class InvalidTaskException extends DukeException {
    /**
     * Constructor for an InvalidTaskException.
     */
    public InvalidTaskException() {
        super("Are you sure about that? There is no such task!");
    }
}
