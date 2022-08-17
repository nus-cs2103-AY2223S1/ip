public class DENoArgException extends TumuException {
    private static String NO_ARG_EXCEPTION =
            "\tPlease fill in the task and/or timing!";

    public DENoArgException() {
        super(NO_ARG_EXCEPTION);
    }
}
