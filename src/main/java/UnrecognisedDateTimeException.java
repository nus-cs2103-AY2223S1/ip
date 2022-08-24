public class UnrecognisedDateTimeException extends TumuException {
    private static String DATE_TIME_ERROR = "\tError with reading date and time! " +
            "Please add in your date and time in yyyy-MM-dd HHmm format.";

    public UnrecognisedDateTimeException() {
        super(DATE_TIME_ERROR);
    }
}
