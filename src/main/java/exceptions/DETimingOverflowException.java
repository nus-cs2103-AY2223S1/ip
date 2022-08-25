package exceptions;

public class DETimingOverflowException extends TumuException {
    private static String TIMING_OVERFLOW_EXCEPTION =
            "There's too many timings, I'm confused. ◔_◔";

    public DETimingOverflowException() {
        super(TIMING_OVERFLOW_EXCEPTION);
    }
}
