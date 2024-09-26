package duke.exception;

/**
 * A class representing an unknown command exception.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Command Not Found");
    }
}
