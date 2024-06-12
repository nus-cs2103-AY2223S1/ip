package duke.exception;

/**
 * An exception class that wraps the built-in IOException. This occurs upon unexpected I/O errors.
 */
public class DukeIoException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeIoException(String message) {
        super(message);
    }
}
