package duke;

/**
 * The DukeException class encapsulates exceptions that occur when Duke is running.
 */
public class DukeException extends Exception {
    /**
     * Initializes an instance of DukeException containing the detail message.
     *
     * @param message Detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
