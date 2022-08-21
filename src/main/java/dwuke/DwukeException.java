package dwuke;

/**
 * This class represents exceptions specific to Dwuke.
 */
public class DwukeException extends Exception {
    /**
     * Creates a DwukeException with the given message.
     *
     * @param message The message for the DwukeException.
     */
    public DwukeException(String message) {
        super("oops!!! " + message);
    }
}
