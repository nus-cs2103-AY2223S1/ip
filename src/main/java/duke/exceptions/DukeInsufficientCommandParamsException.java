package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when encountering a user command with insufficient parameters.
 */
public class DukeInsufficientCommandParamsException extends DukeException {

    /**
     * Constructs a DukeInsufficientCommandParamsException exception.
     */
    public DukeInsufficientCommandParamsException() {
        super("Exception: Insufficient command parameters.");
    }
}
