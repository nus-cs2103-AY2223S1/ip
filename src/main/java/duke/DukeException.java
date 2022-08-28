package duke;

/**
 * Represents any exception the Duke application can encounter.
 */
public class DukeException extends Exception {

    /**
     * Initialise the Exception with a message.
     * @param message Message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }

}
