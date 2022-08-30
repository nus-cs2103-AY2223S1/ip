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
        super("Aaaaaaah, I don't understand you :(\n" + "Try adding a valid command (todo, deadline, event)");
    }
}
