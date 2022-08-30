package duke.exception;

/**
 * Throws an exception when the date and time format is incorrect.
 */
public class UnexpectedDateTimeFormatException extends DukeException {
    /**
     * Throws an error message when date and time format is incorrect.
     */
    public UnexpectedDateTimeFormatException() {
        super("Wrong date and time format! Please give in the format DD/MM/YYYY HHmm");
    }
}
