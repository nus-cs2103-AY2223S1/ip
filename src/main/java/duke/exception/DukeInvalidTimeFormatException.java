package duke.exception;

/**
 * DukeInvalidTimeFormatException Class.
 * Exception when user time input is invalid.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidTimeFormatException extends DukeException {
    /**
     * Constructor for DukeInvalidTimeFormatException.
     */
    public DukeInvalidTimeFormatException() {
        super("Please format date and time in YYYY-MM-DD hhmm.");
    }
}
