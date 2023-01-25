package duke;

/**
 * Represent exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs an instance of DukeException which inherits from Exception.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
