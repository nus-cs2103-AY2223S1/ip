package kirby.exceptions;

/**
 * KirbyException class implements the exception class specific to this program.
 */
public class KirbyException extends Exception {
    /**
     * Constructor for the class KirbyException.
     *
     * @param message error message.
     */
    public KirbyException(String message) {
        super(message);
    }
}
