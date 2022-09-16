package exceptions;

/**
 * Used when the task index being accessed is invalid..
 */
public class InvalidTaskIndexException extends DukeException {
    /**
     * Constructs an exception that indicates that the task index specified cannot be found.
     */
    public InvalidTaskIndexException() {
        super("🥚 OOPS!!! The task at the index specified cannot be found.\n");
    }
}
