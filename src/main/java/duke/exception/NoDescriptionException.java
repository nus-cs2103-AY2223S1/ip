package duke.exception;

/**
 * Throws an exception when description is provided but not needed.
 */
public class NoDescriptionException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION = "We expect no other description after ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " command!";
    /**
     * Throws an error message indicating that the given command do not need a description.
     *
     * @param command command that does not require description.
     */
    public NoDescriptionException(String command) {
        super(EXCEPTION_FRONT_DESCRIPTION + command + EXCEPTION_BACK_DESCRIPTION);
    }
}
