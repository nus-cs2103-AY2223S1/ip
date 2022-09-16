package duke.exception;

/**
 * Thrown when Duke encounters an invalid date time format.
 *
 * @author Lim Ai Lin
 */
public class DukeInvalidDateTimeException extends DukeException {

    /**
     * Creates a DukeInvalidDateTimeException.
     */
    public DukeInvalidDateTimeException() {
        super("RAWR! Please enter date and time in the format: dd/M/yyyy HH:mm");
    }
}
