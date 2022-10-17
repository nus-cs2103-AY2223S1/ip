package duke.exception;

/**
 * Exception class for Duke.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class DukeException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the error message to be printed out
     */
    public DukeException(String message) {
        super(message);
    }
}
