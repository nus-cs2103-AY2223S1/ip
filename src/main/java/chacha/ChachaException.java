package chacha;

/**
 *  Handles all Chacha exceptions.
 */
public class ChachaException extends Exception {
    /**
     * Constructs ChachaException with custom message.
     * 
     * @param message Custom error message to be printed.
     */
    public ChachaException(String message) {
        super(message);
    }
}