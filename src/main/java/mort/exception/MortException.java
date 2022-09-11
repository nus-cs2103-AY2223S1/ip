package mort.exception;

/**
 * Class to handle Mort Exceptions.
 */
public class MortException extends Exception {
    /**
     * Constructor for MortException with no detail message.
     */
    public MortException() {
    }

    /**
     * Constructor for Mort Exception with specified detail message.
     * @param msg The error message to be displayed.
     */
    public MortException(String msg) {
        super(msg);
    }
}
