package duke.exception;

/**
 * DukeInvalidDateException is a DukeException when a date is invalid.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeInvalidDateException extends DukeException {
    /**
     * Constructor for DukeInvalidDateException.
     */
    public DukeInvalidDateException() {
        super("It seems like you are using an invalid date format. Please input your date in the format " +
                "yyyy-mm-dd");
    }
}
