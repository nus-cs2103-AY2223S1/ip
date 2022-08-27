package cheese.exception;

/**
 * Represents a custom exception specific to <code>Cheese</code>.
 */
public class CheeseException extends Exception {
    /**
     * Consructs an instance of <code>CheeseException</code> with default message.
     */
    public CheeseException() {
        super("Sowwy, I don't understand");
    }

    /**
     * Constructs an instance of <code>CheeseException</code>.
     * 
     * @param message Message of exception.
     */
    public CheeseException(String message) {
        super(message);
    }
}
