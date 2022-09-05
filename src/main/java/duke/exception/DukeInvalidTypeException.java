package duke.exception;

/**
 * DukeInvalidTypeException Class.
 * Exception when type specified does not exist.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidTypeException extends DukeException {
    /**
     * Constructor for DukeInvalidTypeException.
     */
    public DukeInvalidTypeException() {
        super("Please provide a valid type :)");
    }
}
