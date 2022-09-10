package duke.exception;

/**
 * DukeInvalidDeadlineSeparatorException Class.
 * Exception when deadline did not use valid separator.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidDeadlineSeparatorException extends DukeException {
    /**
     * Constructor for DukeInvalidDeadlineSeparatorException.
     */
    public DukeInvalidDeadlineSeparatorException() {
        super("Please use /by when separating deadline description and time");
    }
}
