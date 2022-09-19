package duke.exceptions;

/**
 * This exception indicates that the supplied string does not match a recognised
 * format for a date.
 */
public class UnrecognisedDateException extends Exception {

    public UnrecognisedDateException() {
        super("The date you entered in not in a recognised format.");
    }

}
