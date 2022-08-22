package duke.exception;

/**
 * Exception for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class DukeException extends Exception {
    /**
     * Constructs new default DukeException.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm Sorry, Yem doesn't know what that means.");
    }

    /**
     * Constructs new DukeException based on error string.
     *
     * @param error the error message string.
     */
    public DukeException(String error) {
        super("☹ OOPS!!! " + error);
    }
}
