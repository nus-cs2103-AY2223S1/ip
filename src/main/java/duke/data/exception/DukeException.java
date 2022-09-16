package duke.data.exception;

/**
 * This class encapsulates the Duke Exception
 * The exception is thrown when the chatbot encounters invalid inputs
 */
public class DukeException extends Exception {

    /**
     * Constructs an exception
     * @param message The message to display when the exception is thrown and caught
     */
    public DukeException(String message) {
        super(String.format("Oops! %s", message));
    }
}
