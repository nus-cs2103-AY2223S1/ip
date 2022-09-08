package mort.exception;

/**
 * Class to handle Duke Exceptions.
 */
public class MortException extends Exception {
    /**
     * Constructor for DukeException with no detail message.
     */
    public MortException() {
    }

    /**
     * Constructor for DukeException with specified detail message.
     * @param msg The error message to be displayed.
     */
    public MortException(String msg) {
        super(msg);
    }
}
