package amanda.exception;

/**
 * AmandaException is an exception thrown when amanda doesn't understand the user input.
 */
public class AmandaException extends Exception {

    /**
     * Constructor for AmandaException class
     * @param error error message.
     */
    public AmandaException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "Amanda Exception: " + getMessage();
    }
}
