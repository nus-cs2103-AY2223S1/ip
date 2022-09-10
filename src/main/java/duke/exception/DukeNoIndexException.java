package duke.exception;

/**
 * DukeNoIndexException Class.
 * Exception when index not provided for commands that needs it.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeNoIndexException extends DukeException {
    /**
     * Constructor for DukeNoIndexException.
     */
    public DukeNoIndexException() {
        super("Please specify the index for the task.");
    }
}
