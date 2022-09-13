package duke.exception;

/**
 * Throws an exception when description is expected but not given.
 */
public class DescriptionNotSpecifyException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION = "We expect some description to be specified after ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " command!\nIt should not be empty!";
    /**
     * Throws an error message stating that description is required.
     *
     * @param command commands which requires a description.
     */
    public DescriptionNotSpecifyException(String command) {
        super(EXCEPTION_FRONT_DESCRIPTION + command + EXCEPTION_BACK_DESCRIPTION);
    }
}
