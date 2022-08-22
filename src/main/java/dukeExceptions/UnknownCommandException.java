package dukeExceptions;

/**
 * Thrown when an unknown command (e.g. "blah") is passed into Duke. Commands are
 * defined in the COMMANDS enum.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("OOPS!!! Sorry, I do not know what that means!");
    }
}
