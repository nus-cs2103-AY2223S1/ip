package gina;

/**
 * Custom exception for the chatbot.
 */
public class GinaException extends Exception {
    /**
     * Constructs an exception with the specified message.
     *
     * @param message The specified error message.
     */
    public GinaException(String message) {
        super(message);
    }
}
