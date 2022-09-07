package duke.exception;

/**
 * A class representing invalid date format exception.
 */
public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Please Input a Valid Date");
    }
}
