package duke;

/**
 * Representation of user-related errors for Duke.
 */
public class DukeException extends Exception {
    private final String message;

    /**
     * Creates new DukeException with specified message.
     *
     * @param message Error to be displayed to the user.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns DukeException message.
     *
     * @return Message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns String representation of DukeException - the message.
     *
     * @return Message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}

