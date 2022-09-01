package duke;

/**
 * Custom exception for the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs an exception with the specified message.
     *
     * @param message The specified error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
