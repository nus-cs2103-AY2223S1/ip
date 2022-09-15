package duke.exceptions;

/**
 * Main Exception class that Duke uses.
 */
public class DukeException extends Exception {

    /**
     * Standard constructor for the DukeException class.
     *
     * @param message message that the exception will throw
     */
    public DukeException(String message) {
        super(message);
    }
}
