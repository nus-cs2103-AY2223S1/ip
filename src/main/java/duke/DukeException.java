package duke;

/**
 * Handles exceptions in Duke program relating to invalid arguments.
 */
public class DukeException extends IllegalArgumentException {
    /**
     * DukeException constructor.
     *
     * @param message Message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }

}
