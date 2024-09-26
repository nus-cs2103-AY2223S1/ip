package duke;

/**
 * DukeException is an exception class when using Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs of a DukeException object.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return "Oh no! " + super.getMessage();
    }

}
