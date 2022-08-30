package duke.exception;

/**
 * DukeException is parent class of all exceptions related to incorrect input by user
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     *
     * @param message message to be displayed to user when caught
     */
    public DukeException(String message) {
        super(message);
    }
}
