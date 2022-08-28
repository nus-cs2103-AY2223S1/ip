package duke;

/**
 * Encapsulates an Exception related to the Duke chatbot.
 *
 */
public abstract class DukeException {
    private String message;

    /**
     * Constructor.
     *
     * @param message
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns String representation of the exception.
     *
     * @return String representation of the exception
     */
    public String toString() {
        return this.message;
    }
}
