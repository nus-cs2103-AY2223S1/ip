package duke.exception;

/**
 * DukeInvalidEventSeparatorException Class.
 * Exception when event did not use valid separator.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidEventSeparatorException extends DukeException {
    /**
     * Constructor for DukeInvalidEventSeparatorException.
     */
    public DukeInvalidEventSeparatorException() {
        super("Please use /at when separating event description and time");
    }
}
