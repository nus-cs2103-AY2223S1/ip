package kirby.exceptions;

/**
 * KirbyMissingArgumentException class inherits from KirbyException and
 * is thrown when there is an invalid input command argument.
 */
public class KirbyMissingArgumentException extends KirbyException {
    /**
     * Constructor for the class KirbyMissingArgumentException.
     *
     * @param message type of task that is producing the error message.
     */
    public KirbyMissingArgumentException(String message) {
        super("Aaaaaaah, the description of a " + message + " is invalid! Try again!" );
    }
}
