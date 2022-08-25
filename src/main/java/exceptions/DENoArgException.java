package exceptions;

/**
 * No argument exception. No argument given by the
 * user when calling the deadline or event command.
 */
public class DENoArgException extends TumuException {
    private static String NO_ARG_EXCEPTION =
            "Please fill in the task and/or timing!";

    /**
     * Constructor for the DENoArgException class.
     */
    public DENoArgException() {
        super(NO_ARG_EXCEPTION);
    }
}
