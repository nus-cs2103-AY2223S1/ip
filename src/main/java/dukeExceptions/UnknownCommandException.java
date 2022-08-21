package dukeExceptions;

/**
 * Thrown when an unknown command (e.g. "blah") is passed into Duke. Commands are
 * defined in the COMMANDS enum.
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super(String.format("OOPS!!! Sorry, I do not know what that means!"));
    }
}
