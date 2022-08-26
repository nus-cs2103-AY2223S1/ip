package exceptions;

/**
 * Used when the datetime field entered does not match the date time format
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Constructs an exception that indicates that the datetime input does not follow the specified date time
     * format
     *
     * @param dateTimeFormat The specified date time format.
     */
    public InvalidDateTimeException(String dateTimeFormat) {
        super("     ☹ OOPS!!! The datetime specified is invalid, it should have the format ");
    }
}
