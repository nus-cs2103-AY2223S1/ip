package Duke.exceptions;

/**
 * {@code InvalidTaskNameException} is an exception thrown when a
 * task name provided by the user is invalid.
 */
public class InvalidTaskNameException extends DukeException {
    /**
     * This exception is thrown when a user provided an invalid task name.
     */
    public InvalidTaskNameException() {
        super("Please include a valid task name");
    }
}