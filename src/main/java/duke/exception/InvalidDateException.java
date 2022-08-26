package duke.exception;

public class InvalidDateException extends DukeException {

    /**
     * Constructor for InvalidDateException.
     *
     */
    public InvalidDateException() {
        super();
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
