package duke.exceptions;

/**
 * Exception for the Duke application.
 */
public class DukeException extends Exception {
    // Some commonly used exceptions.
    public static final DukeException INVALID_INDEX = new DukeException("Invalid index!");
    public static final DukeException INVALID_DATE = new DukeException("Invalid date!");
    public static final DukeException BAD_DATA = new DukeException("Bad tasks data!");

    /**
     * Constructor for a Duke Exception, that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
