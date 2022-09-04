package duke;

/**
 * Represents custom errors in programme.
 */
public class DukeException extends Exception {

    /**
     * Constructs a custom error.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

}
