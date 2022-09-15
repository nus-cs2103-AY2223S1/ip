package duke.exception;

/**
 * Representation for invalid event command exception.
 */
public class InvalidEventException extends DukeException {
    private static final String message = "Hmm, input doesn't match the 'event' format."
            + "\ne.g event team meeting /at 2022-10-10 19:00";

    /**
     * Constructor for InvalidEventException.
     */
    public InvalidEventException() {
        super(message);
    }

    /**
     * Constructor to set custom error message.
     * @param message Error message.
     */
    public InvalidEventException(String message) {
        super(message);
    }
}
