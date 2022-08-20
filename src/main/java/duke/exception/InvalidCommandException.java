package duke.exception;

/**
 * Represents exception when invalid command is run.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs an InvalidCommandException.
     */
    public InvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
