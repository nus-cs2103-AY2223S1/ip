package exception;

/**
 * <h1>DukeException</h1>
 * Exception to be thrown when the chat bot has unintended or wrong
 * outcomes.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Creates the DukeException object.
     *
     * @param message error message to be returned.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the String representation of the DukeException.
     *
     * @return the String representation of the DukeException.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
