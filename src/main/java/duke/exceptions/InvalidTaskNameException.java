package duke.exceptions;

/**
 * {@code InvalidTaskNameException} is an exception thrown when a
 * task name provided by the user is invalid.
 */
public class InvalidTaskNameException extends DukeException {
    /**
     * Constructs an invalid task name exception.
     */
    public InvalidTaskNameException() {
        super("Please include a valid task name");
    }
}