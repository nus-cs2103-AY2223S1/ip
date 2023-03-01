package duke;

/**
 * Exception to be thrown during input errors when running Duke.
 */
public class DukeException extends Exception {
    /**
     * Throws a DukeException with a specified message.
     * @param message Message to be displayed to the user when a DukeException is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
