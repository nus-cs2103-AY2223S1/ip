package kirby.exceptions;

/**
 * KirbyInvalidCommandException class inherits from KirbyException and
 * is thrown when there is an undefined input command.
 */
public class KirbyInvalidCommandException extends KirbyException {
    /**
     * Constructor for the class KirbyInvalidCommandException.
     */
    public KirbyInvalidCommandException() {
        super("Aaah you have indicated a weird command :3 Type help for more info!");
    }
}
