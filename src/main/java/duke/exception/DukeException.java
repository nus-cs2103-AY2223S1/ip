package duke.exception;

/**
 * Class encapsulating exceptions unique to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException Class.
     *
     * @param message Exception message to be returned.
     */
    public DukeException(String message) {
        super(message);
    }
}
