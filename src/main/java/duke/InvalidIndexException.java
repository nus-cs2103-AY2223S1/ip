package duke;

/**
 * InvalidIndexException is a RuntimeException that is thrown when the user fails to provide a valid index.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for InvalidIndexException.
     */
    public InvalidIndexException() {
        super("Please provide a valid index within the list.");
    }
}
