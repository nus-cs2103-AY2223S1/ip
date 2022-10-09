package duke;

/**
 * This class represents Exceptions specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException with the given message, appended with "Oops!" at the start.
     *
     * @param message The message for the DukeException.
     */
    public DukeException(String message) {
        super("Oops! " + message);
    }
}
