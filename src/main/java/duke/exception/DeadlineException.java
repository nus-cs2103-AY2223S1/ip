package duke.exception;

/**
 * Deadline exception for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class DeadlineException extends DukeException {
    /**
     * Constructs a new default DeadlineException.
     */
    public DeadlineException() {
        super("The description and time limit of deadline cannot be empty.");
    }

    /**
     * Constructs a new DeadlineException based on error string.
     *
     * @param error the error message string.
     */
    public DeadlineException(String error) {
        super(error);
    }
}
