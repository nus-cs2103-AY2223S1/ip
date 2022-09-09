package duke.exception;

/**
 * Throws an exception when an integer is expected but not given.
 */
public class IntegerExpectedException extends DukeException {
    public static final String EXCEPTION_DESCRIPTION = "Expected an Integer!";
    /**
     * Throws an error message indicating that integer is necessary.
     */
    public IntegerExpectedException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
