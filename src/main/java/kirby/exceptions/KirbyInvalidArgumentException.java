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
        super("Aaah you have indicated an invalid argument! " + message + " command is invalid! \nTry again!");
    }
}
