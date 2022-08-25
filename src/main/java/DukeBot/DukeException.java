package DukeBot;

/**
 * An exception specific to the Duke program.
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException with an error as the message.
     *
     * @param message The error captured within the DukeException instance.
     */
    public DukeException(String message) {
        super(message);
    }
}
