package kirby.exceptions;

/**
 * The kirby.KirbyMissingArgumentException class inherits from kirby.KirbyException
 * and is thrown when there is an invalid input command argument.
 * @author Sheryl-Lynn Tan (G11)
 */
public class KirbyMissingArgumentException extends KirbyException {
    public KirbyMissingArgumentException(String message) {
        super("Aaaaaaah, the description of a " + message + " is invalid! Try again!" );
    }
}
