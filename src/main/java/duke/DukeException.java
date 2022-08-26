package duke;

/**
 * Exceptions thrown in Duke
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for new instance
     *
     * @param message message to be printed for users
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns string representation of exception
     *
     * @return string for message
     */
    @Override
    public String toString() {
        return "OOPS!! " + message;
    }
}
