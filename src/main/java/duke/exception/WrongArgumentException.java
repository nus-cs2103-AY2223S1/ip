package duke.exception;

/**
 * Thrown when the argument(s) of a command cannot be parsed properly.
 */
public class WrongArgumentException extends DukeException {
    public WrongArgumentException(String invalidArg, Throwable e) {
        super(invalidArg, e);
    }
}
