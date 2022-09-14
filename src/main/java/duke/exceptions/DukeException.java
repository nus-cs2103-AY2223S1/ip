package duke.exceptions;

/**
 * <code>DukeException</code> represents exceptions thrown by the Duke program.
 *
 * @author ish1506
 */
public class DukeException extends Exception {
    /**
     * Constructs a new <code>DukeException</code> with a message.
     *
     * @param errorMessage the error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
