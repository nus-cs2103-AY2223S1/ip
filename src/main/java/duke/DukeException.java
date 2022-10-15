package duke;

/**
 * Customized exception for use in the Duke Application.
 */
public class DukeException extends Exception {
    public DukeException (String message) {
        super(message);
    }

    /**
     * Returns String representing exception.
     *
     * @return String representation describing the exception thrown
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
