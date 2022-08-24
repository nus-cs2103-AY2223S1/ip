package duke;

/**
 * An exception.
 */
public class DukeException extends Exception {
    /**
     * Initialises an Exception object.
     * @param message message to be parsed as Duke error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * @return Error message for the Exception.
     */
    @Override
    public String getMessage() {
        return "DUKE ERROR: " + super.getMessage();
    }
}
