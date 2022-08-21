package duke.exceptions;

/**
 * Represents an exception in the Duke application.
 */
public class DukeException extends Exception {
    /** Exception due to an invalid index. */
    public static final DukeException INVALID_INDEX = new DukeException("Invalid index!");
    /** Exception due to an invalid date. */
    public static final DukeException INVALID_DATE = new DukeException("Invalid date!");
    /** Exception due to bad tasks data. */
    public static final DukeException BAD_DATA = new DukeException("Bad tasks data!");

    /**
     * Constructor for a Duke Exception that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
