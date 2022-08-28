package exception;

/**
 * This exception is thrown when user input is not a recognized command.
 */
public class WrongCommandException extends DukeException {

    /**
     * Creates a WrongCommandException.
     */
    public WrongCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
