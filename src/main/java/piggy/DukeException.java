package piggy;

/**
 * Exceptions for errors specific to this package.
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException with a given message.
     *
     * @param message The message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message.
     *
     * @return A string with "☹ OOPS!!! " followed by the error message.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
