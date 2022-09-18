package candice.exception;

/**
 * Abstraction for exceptions that are thrown when the command inputted has a command type that is not recognised.
 */
public class UnknownCommandException extends Exception {
    /**
     * Constructor for an exception thrown when the command type is unknown.
     */
    public UnknownCommandException() {
        super("I have no clue what you're trying to do bro.");
    }
}
