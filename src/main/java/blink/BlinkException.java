package blink;

/**
 * Custom RuntimeException that is thrown when a wrong input
 * is passed by users.
 */
public class BlinkException extends RuntimeException {

    /**
     * Constructor for BlinkException.
     *
     * @param message Information of what user input error was found
     */
    public BlinkException(String message) {
        super(message);
    }
}
