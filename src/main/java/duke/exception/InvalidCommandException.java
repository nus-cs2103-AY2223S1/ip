package duke.exception;

/**
 * InvalidCommandException is thrown when the input command is invalid.
 */
public class InvalidCommandException extends DukeExceptions {

    public InvalidCommandException(String msg) {
        super(msg);
    }
}
