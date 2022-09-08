package duke.exception;

/**
 * Throws exception when date and time is expected but not given.
 */
public class DateTimeNotFoundException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION =  " command expects a specified Date and Time after ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " keyword!";
    /**
     * Throws an error message stating that date and time is required.
     *
     * @param command the command that requires date and time
     * @param keyword date and time should be given after this keyword
     */
    public DateTimeNotFoundException(String command, String keyword) {
        super(command + EXCEPTION_FRONT_DESCRIPTION + keyword + EXCEPTION_BACK_DESCRIPTION);
    }
}
