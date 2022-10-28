package exceptions;

/**
 * The exception class that handles errors related
 * to Henry.
 */
public class HenryException extends RuntimeException {

    /**
     * Simple constructor that allows for custom error messages.
     *
     * @param message the error message to be displayed by HenryException
     */
    public HenryException(String message) {
        super(message);
    }
}
