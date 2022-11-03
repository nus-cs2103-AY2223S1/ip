package jude;

/**
 * An unchecked exception thrown when user passes in invalid command to the task tracker chatbot.
 */
public class IllegalCommandException extends RuntimeException {

    /**
     * Creates instance of {@code IllegalCommandException}.
     *
     * @param message The message accompanying the exception.
     */
    public IllegalCommandException(String message) {
        super(message);
    }
}
