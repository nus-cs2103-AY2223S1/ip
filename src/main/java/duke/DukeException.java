package duke;

/**
 * Represents a Duke related exception.
 */
public class DukeException extends RuntimeException {

    /**
     * Creates a new Duke related exception with an error message.
     *
     * @param message Error message to be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }

}
