package duke.exception;

/**
 * An exception class that is thrown when a Command is executed for the second time.
 */
public class DukeCommandAlreadyExecutedException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeCommandAlreadyExecutedException(String message) {
        super(message);
    }
}
