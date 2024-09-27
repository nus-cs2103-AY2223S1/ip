package duke.dukeexception;

/**
 * Represents exception specific to duke program.
 */
public class DukeException extends Exception {
    /**
     * Class constructor.Construct an exception
     * @param message Containing the error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
