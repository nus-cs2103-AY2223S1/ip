package exceptions;

/**
 * Exception occurs when the date and time typed by the user cannot be
 * recognised by the program.
 */
public class UnrecognisedDateTimeException extends TumuException {
    private static final String DATE_TIME_ERROR = "Error with reading date and time! "
            + "Please add in your date and time in yyyy-MM-dd HHmm format.";

    /**
     * Constructor for the UnrecognisedDateTimeException.
     */
    public UnrecognisedDateTimeException() {
        super(DATE_TIME_ERROR);
    }
}
