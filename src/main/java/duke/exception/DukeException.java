package duke.exception;

/**
 * An exception class for the Duke program.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class DukeException extends IllegalArgumentException {
    /**
     * A constructor to initialize the exception.
     *
     * @param message The message for the exception.
     */
    public DukeException(String message){
        super(message);
    }
}
