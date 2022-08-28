package duke.exception;

/**
 * Class to handle Duke Exceptions.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException with no detail message.
     */
    public DukeException() {
    }

    /**
     * Constructor for DukeException with specified detail message.
     * @param msg The error message to be displayed.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
