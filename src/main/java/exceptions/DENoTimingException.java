package exceptions;

/**
 * Exception occurs when no timing is given when the deadline
 * or event command is issued.
 */
public class DENoTimingException extends TumuException {
    /**
     * Constructor for the DENoTimingException class.
     * @param command Command for either deadline (/by) or
     *                event (/at)
     */
    public DENoTimingException(String command) {
        super(String.format("Remember to add a timing for the " +
                "deadline/event using /%s!", command));
    }
}
