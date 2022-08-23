package duke;

/**
 * Custom exception for Duke.
 *
 * @author Aaron Tan
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     *
     * @param message Exception message to be created.
     */
    public DukeException(String message) {
        super(message);
    }
}
