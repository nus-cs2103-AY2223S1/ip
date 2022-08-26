package duke.exceptions;

/**
 * Thrown when index goes out of bounds of a list/array.
 */
public class OutOfBoundException extends DukeException {
    private static final String DESCRIPTION = "The provided index number is invalid!";

    public OutOfBoundException() {
        super(DESCRIPTION);
    }
}
