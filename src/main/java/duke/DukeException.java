package duke;

/**
 * DukeException is an unchecked exception that is thrown when Duke does not recognise the input by the user.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class DukeException extends RuntimeException {
    /**
     * A constructor for DukeException.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
