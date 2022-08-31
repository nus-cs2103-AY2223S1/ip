package duke.exception;

/**
 * DukeOutOfBoundException Class.
 * Exception when index provided is outside the size of a list.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeOutOfBoundException extends DukeException {
    /**
     * Constructor for DukeOutOfBoundException.
     */
    public DukeOutOfBoundException() {
        super("Boo... Task don't exist... It's out of bound! :(");
    }
}
