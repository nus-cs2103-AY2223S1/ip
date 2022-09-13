package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when encountering a user command with incorrect parameters.
 */
public class DukeIncorrectCommandParamsException extends DukeException {

    /**
     * Constructs a DukeIncorrectCommandParamsException exception.
     */
    public DukeIncorrectCommandParamsException() {
        super("Exception: Incorrect command parameters.");
    }
}
