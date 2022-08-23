package duke.common;

/**
 * Exception thrown by the application
 *
 * @author Pontakorn Prasertsuk
 */
public class DukeException extends Exception {

    /**
     * Constructs a new default DukeException instance
     */
    public DukeException() {
        super("Sorry, something went wrong!");
    }

    /**
     * Constructs a new DukeException instance
     *
     * @param message the message to be shown
     */
    public DukeException(String message) {
        super(message);
    }
}
