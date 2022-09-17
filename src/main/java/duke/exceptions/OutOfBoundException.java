package duke.exceptions;

/**
 * Thrown when index goes out of bounds of a list/array.
 */
public class OutOfBoundException extends DukeException {
    private static final String DESCRIPTION = "Bruh. Do you want more task?";

    public OutOfBoundException() {
        super(DESCRIPTION);
    }
}
