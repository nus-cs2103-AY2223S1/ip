package john.data.exception;

/**
 * Signals an exception in the program.
 */
public class JohnException extends Exception {
    public JohnException(String message) {
        super(String.format("%s", message));
    }
}
