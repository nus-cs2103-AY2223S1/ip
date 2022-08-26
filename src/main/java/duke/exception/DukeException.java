package duke.exception;

/**
 * The base class of all other Duke-specific exceptions.
 */
public class DukeException extends Exception {

    /**
     * The standard constructor.
     */
    public DukeException(String message) {
        super(message);
    }
}
