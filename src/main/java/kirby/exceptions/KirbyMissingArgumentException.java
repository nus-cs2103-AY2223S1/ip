package kirby.exceptions;

/**
 * KirbyMissingArgumentException class inherits from KirbyException and
 * is thrown when there is an invalid input command argument.
 */
public class KirbyMissingArgumentException extends KirbyException {
    /**
     * Constructor for the class KirbyMissingArgumentException.
     *
     * @param message Type of task that is producing the error message.
     */
    public KirbyMissingArgumentException(String message) {
        super("Aaaaaaah, the description of a " + message + "\n" + "is invalid! \n Try again!");
    }
}
