package exceptions;

/**
 * The exception class that handles errors related
 * to Henry.
 */
public class HenryException extends RuntimeException {

    public HenryException(String message) {
        super(message);
    }
}
