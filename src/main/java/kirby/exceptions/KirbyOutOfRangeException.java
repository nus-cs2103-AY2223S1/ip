package kirby.exceptions;

/**
 * KirbyMissingArgumentException class inherits from KirbyException and
 * is thrown when the argument given is out of the range of the array index.
 */
public class KirbyOutOfRangeException extends KirbyException {
    /**
     * Constructor for the class KirbyOutOfRangeException.
     *
     * @param message Type of task that is producing the error message.
     */
    public KirbyOutOfRangeException(String message) {
        super("Aaah you have indicated an invalid index! " + message + " command is invalid! \nTry again!");
    }
}
