public class DETimingOverflowException extends TumuException {
    private static String TIMING_OVERFLOW_EXCEPTION =
            "\tThere's too many timings, I'm confused. ◔_◔";

    public DETimingOverflowException() {
        super(TIMING_OVERFLOW_EXCEPTION);
    }
}
