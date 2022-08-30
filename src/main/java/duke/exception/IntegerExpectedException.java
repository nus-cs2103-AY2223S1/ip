package duke.exception;

/**
 * Throws an exception when an integer is expected but not given.
 */
public class IntegerExpectedException extends DukeException {
    /**
     * Throws an error message indicating that integer is necessary.
     */
    public IntegerExpectedException() {
        super("Expected an Integer!");
    }
}
