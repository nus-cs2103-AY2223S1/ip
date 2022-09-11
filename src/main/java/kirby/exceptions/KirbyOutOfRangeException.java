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
        super("Aaaaaaah, the description of a " + message + "\n" + "is out of range! \n Try again!");
    }
}
