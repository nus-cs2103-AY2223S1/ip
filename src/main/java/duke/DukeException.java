package duke;

/**
 * Represents Exceptions that arise from Duke's features.
 */
public class DukeException extends Exception {
    /**
     * Creates DukeException with the given message.
     *
     * @param message The message of the DukeException.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the DukeException.
     *
     * @return The String representation of the DukeException.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! Duke got an error!\n" + super.getMessage();
    }
}
