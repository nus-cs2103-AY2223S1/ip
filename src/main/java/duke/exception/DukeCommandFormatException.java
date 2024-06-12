package duke.exception;

/**
 * An exception class that is thrown when a command does not follow its expected format.
 */
public class DukeCommandFormatException extends DukeException {
    /**
     * The standard constructor.
     */
    public DukeCommandFormatException(String message) {
        super(message);
    }

}
