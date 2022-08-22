package duke.exception;

/**
 * DukeInvalidDeadlineException Class.
 * Exception when Time provided is of invalid format
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidTimeException extends DukeException {
    /**
     * Constructor for DukeInvalidTimeException.
     */
    public DukeInvalidTimeException() {
        super("OOPS!!! I cant parse the time provided by you!\n"
                + "Please input the time in 24hr format! (i.e. 1230)");
    }
}
