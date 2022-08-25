package exceptions;

/**
 * Base exception class for the program.
 */
public abstract class TumuException extends Exception {
    private String message;

    /**
     * Constructor for the TumuException class.
     * @param message Message to be displayed to the user.
     */
    protected TumuException(String message) {
        this.message = message;
    }

    /**
     * Returns the message to the user.
     * @return Message to the user.
     */
    @Override
    public String toString() {
        return message;
    }
}
