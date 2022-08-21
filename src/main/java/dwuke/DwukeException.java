package dwuke;

/**
 * This class represents Exceptions specific to Dwuke.
 */
public class DwukeException extends Exception {
    /**
     * Creates a DwukeException with the given message, appended with "oops!!!" at the start.
     *
     * @param message The message for the DwukeException.
     */
    public DwukeException(String message) {
        super("oops!!! " + message);
    }
}
