/**
 * The KirbyMissingArgumentException class inherits from KirbyException
 * and is thrown when there is an invalid input command argument.
 * @author Sheryl-Lynn Tan (G11)
 */
public class KirbyMissingArgumentException extends KirbyException {
    /**
     * Constructor for KirbyMissingArgumentException.
     * @param message to print.
     */
    KirbyMissingArgumentException(String message) {
        super("T_T Aaaaaaah, the description of a " + message + " is invalid! Try again!" );
    }
}
