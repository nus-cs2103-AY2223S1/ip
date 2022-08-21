package duke;

/**
 * Exception raised in Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * 
     * @param message error message.
     * @param error error that resulted in this error.
     */
    public DukeException(String message, Throwable error) {
        super(message, error);
    }

    /**
     * Constructor for DukeException.
     * 
     * @param message error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no! " + super.getMessage();
    }
}
