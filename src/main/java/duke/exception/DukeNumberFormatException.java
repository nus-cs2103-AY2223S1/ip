package duke.exception;

/**
 * DukeNumberFormatException is a DukeException when the number format is invalid.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeNumberFormatException extends DukeException {
    /**
     * Constructor for DukeNumberFormatException.
     */
    public DukeNumberFormatException() {
        super("You have entered an invalid number format. Please try again.");
    }
}
