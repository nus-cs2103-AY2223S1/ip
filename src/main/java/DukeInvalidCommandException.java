/**
 * DukeInvalidCommandException Class.
 * Exception when command provided is invalid.
 *
 * CS2103T IP
 * AY22/23 Semester 1
 * @author Tan Jia Rong
 */
public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
