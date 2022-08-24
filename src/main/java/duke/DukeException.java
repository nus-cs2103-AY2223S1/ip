package duke;

/**
 * Exception thrown in Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructor for {@Code DukeException}.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }


    /**
     * Returns a String representation of {@code DukeException} in display format.
     */
    @Override
    public String toString() {
        return "Error: " + super.getMessage();
    }
}
