package exceptions;

/**
 * Exception when the user tries to access a task index that does not exist.
 */
public class InvalidTaskIndexException extends Exception {
    /**
     * Constructor for this exception.
     */
    public InvalidTaskIndexException() {
        super("There are no tasks with that index...");
    }
}
