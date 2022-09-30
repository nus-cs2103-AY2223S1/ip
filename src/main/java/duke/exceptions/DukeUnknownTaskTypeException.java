package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when encountering an unknown task type.
 */
public class DukeUnknownTaskTypeException extends DukeException {

    /**
     * Constructs a DukeUnknownTaskTypeException exception.
     */
    public DukeUnknownTaskTypeException() {
        super("Exception: Unknown task type encountered.", "So... what is this task?");
    }
}
