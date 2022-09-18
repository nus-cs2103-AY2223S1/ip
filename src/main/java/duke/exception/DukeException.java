package duke.exception;

/**
 * Represents any checked exception the Duke application can encounter.
 */
public class DukeException extends Exception {

    /**
     * Initialises the Exception with a message.
     * @param message Message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }

}
