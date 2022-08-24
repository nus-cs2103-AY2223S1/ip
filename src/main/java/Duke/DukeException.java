package duke;

/**
 * DukeException object class.
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Creates a DukeException object when there is an exception.
     *
     * @param message Error message rendered.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Gets the error message from DukeException object.
     *
     * @return String of error message.
     */
    public String getMessage() {
        return this.message;
    }
}
