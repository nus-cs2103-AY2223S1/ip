package duke.exceptions;

/**
 * Represents an exception in the Duke application.
 */
public class DukeException extends Exception {
    /** Exception due to an invalid index. */
    public final static DukeException invalidIndex = new DukeException("Invalid index!");
    /** Exception due to an invalid date. */
    public final static DukeException invalidDate = new DukeException("Invalid date!");
    /** Exception due to bad tasks data. */
    public final static DukeException badData = new DukeException("Bad tasks data!");

    /**
     * Constructor for a Duke Exception that takes in a message.
     *
     * @param message Message for the Duke Exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
