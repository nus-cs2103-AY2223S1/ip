package exceptions;

/**
 * {@code InvalidTaskNameException} is an exception thrown when a
 * task name provided by the user is invalid.
 */
public class InvalidTaskNameException extends DukeException {
    public InvalidTaskNameException() {
        super("Please include a valid task name");
    }
}