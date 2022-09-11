package kirby.exceptions;

/**
 * KirbyInvalidArgumentException class inherits from KirbyException and
 * is thrown when there is an invalid input command argument.
 */
public class KirbyInvalidArgumentException extends KirbyException {
    /**
     * Constructor for the class KirbyInvalidArgumentException.
     *
     * @param message Type of task that is producing the error message.
     */
    public KirbyInvalidArgumentException(String message) {
        super("Aaaaaaah, the description of a " + message + "\n" + "is invalid! \n Try again!");
    }
}
