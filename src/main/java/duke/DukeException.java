package duke;

/**
 * Represents the DukeException.
 */
public class DukeException extends RuntimeException {
    String message;

    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns a String representation of the message.
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.message;
    }
}
