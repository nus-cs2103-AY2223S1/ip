package exceptions;

/**
 * Used when an invalid command is entered.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs a new invalid command exception.
     */
    public InvalidCommandException() {
        super("😅 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
