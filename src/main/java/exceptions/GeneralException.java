package exceptions;

/**
 * General exception to print an exception message directly.
 */
public class GeneralException extends TumuException {
    /**
     * Constructor for the GeneralException class.
     * @param message Message to be displayed to the user.
     */
    public GeneralException(String message) {
        super(message);
    }
}
