package duke.exception;

/**
 * A class representing index out of bounds exception.
 */
public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("Please specify a valid index");
    }
}
