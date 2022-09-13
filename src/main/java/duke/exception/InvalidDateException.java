package duke.exception;

/**
 * The InvalidDateException class represents a DukeException that is thrown when
 * the user inputs a Deadline or Event with an invalid date format.
 *
 * @author Edric Yeo
 */
public class InvalidDateException extends DukeException {
    /**
     * Creates an EmptyDateException instance.
     */
    public InvalidDateException() {
        super("Invalid Date format! Use a YYYY-MM-DD format!");
    }
}
