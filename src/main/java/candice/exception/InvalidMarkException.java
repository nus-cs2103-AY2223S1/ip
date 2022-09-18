package candice.exception;

/**
 * Abstraction for exceptions that are thrown when a command to mark a task that has already been marked as finished or
 * a command to unmark a task that has already been marked as unfinished is inputted.
 */
public class InvalidMarkException extends Exception {
    /**
     * Constructor for an exception thrown when an invalid mark or unmark command is inputted.
     */
    public InvalidMarkException(String message) {
        super(message);
    }
}
