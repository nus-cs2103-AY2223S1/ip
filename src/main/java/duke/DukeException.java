package duke;

/**
 * DukeException represents the possible exceptions that might occur while using Duke.
 */
public class DukeException extends RuntimeException {
    private final String message;

    /**
     * Constructs a DukeException
     *
     * @param message exception message
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return exception message
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the string representation of this exception.
     *
     * @return a string representing this exception
     */
    public String toString() {
        return "DukeException: " + message;
    }
}
