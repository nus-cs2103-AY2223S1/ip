package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when encountering an unknown user command.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Constructs a DukeUnknownCommandException exception.
     */
    public DukeUnknownCommandException() {
        super("Exception: Unknown user command encountered.", "Are we speaking the same language?");
    }
}
