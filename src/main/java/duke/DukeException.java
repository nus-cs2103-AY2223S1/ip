package duke;

/**
 * DukeException is a RuntimeException that is thrown when the user provides an invalid input.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeException extends RuntimeException {
    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
