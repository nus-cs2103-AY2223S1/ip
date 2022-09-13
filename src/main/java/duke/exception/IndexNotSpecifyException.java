package duke.exception;

/**
 * Throws an exception when an index is expected but not given.
 */
public class IndexNotSpecifyException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION = "We expects an index to execute ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " command!";
    /**
     * Throws an error message when index is not given.
     *
     * @param command command which requires an index.
     */
    public IndexNotSpecifyException(String command) {
        super(EXCEPTION_FRONT_DESCRIPTION + command + EXCEPTION_BACK_DESCRIPTION);
    }
}
