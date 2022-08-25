package duke;

/**
 * The DukeException class represents an exception
 * that is unique to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException.
     */
    public DukeException() {
    }

    /**
     * Constructs a new DukeException with an error message.
     *
     * @param message A string that describes the error which occurred.
     */
    public DukeException(String message) {
        super(message);
    }
}
