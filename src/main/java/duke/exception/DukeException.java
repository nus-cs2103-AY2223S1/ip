package duke.exception;

/**
 * A class that encapsulates the exceptions from the Duke program.
 */
public class DukeException extends Exception {
    /**
     * The class constructor.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Converts the DukeException to its string representation.
     *
     * @return The string representing the DukeException.
     */
    @Override
    public String toString() {
        return "☹ OOPS! " + super.getMessage();
    }
}
