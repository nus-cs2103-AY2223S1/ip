package exceptions;

/**
 * No argument exception. No argument given by the
 * user when calling the deadline or event command.
 */
public class DeadlineEventNoArgException extends TumuException {
    private static final String NO_ARG_EXCEPTION =
            "Please fill in the task and/or timing!";

    /**
     * Constructor for the DENoArgException class.
     */
    public DeadlineEventNoArgException() {
        super(NO_ARG_EXCEPTION);
    }
}
