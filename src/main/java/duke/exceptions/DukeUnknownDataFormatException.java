package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when encountering an unknown data string format.
 */
public class DukeUnknownDataFormatException extends DukeException {

    /**
     * Constructs a DukeUnknownDataFormatException exception.
     */
    public DukeUnknownDataFormatException() {
        super("Exception: Unknown data string format encountered.");
    }
}
