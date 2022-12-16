package duke.exception;

/**
 * Class to encapsulate error when date is not of right format.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructor for InvalidDateException.
     *
     */
    public InvalidDateException() {
        super("Date input invalid, please follow: dd/MM/yyyy HHmm");
    }

    /**
     * Returns the String representation of error.
     *
     * @return The String representation of error.
     */
    @Override
    public String toString() {
        return "Date input invalid, please follow: dd/MM/yyyy HHmm";
    }
}
