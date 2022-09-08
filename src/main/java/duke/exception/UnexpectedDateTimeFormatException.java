package duke.exception;

/**
 * Throws an exception when the date and time format is incorrect.
 */
public class UnexpectedDateTimeFormatException extends DukeException {
    public static String EXCEPTION_DESCRIPTION =
            "Wrong date and time format! Please give in the format DD/MM/YYYY HHmm";
    /**
     * Throws an error message when date and time format is incorrect.
     */
    public UnexpectedDateTimeFormatException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
