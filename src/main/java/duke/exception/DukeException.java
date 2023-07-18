package duke.exception;

/**
 * DukeException is an Exception specific to Duke.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message Error message of exception.
     */
    public DukeException(String message) {
        super("Uh oh!!! " + message);
    }
}
