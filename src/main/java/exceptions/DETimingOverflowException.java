package exceptions;

/**
 * Exception occurs if there are too many timings indicated
 * by the user for the deadline and event commands.
 */
public class DETimingOverflowException extends TumuException {
    private static String TIMING_OVERFLOW_EXCEPTION =
            "There's too many timings, I'm confused.";

    /**
     * Constructor for the DETimingOverflowException class.
     */
    public DETimingOverflowException() {
        super(TIMING_OVERFLOW_EXCEPTION);
    }
}
