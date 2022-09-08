package chacha;

/**
 *  Handles all Chacha exceptions.
 */
public class ChachaException extends Exception {
    /**
     * Constructor for ChachaException with custom message.
     * 
     * @param message Custom error message to be printed.
     */
    public ChachaException(String message) {
        super(message);
    }
}