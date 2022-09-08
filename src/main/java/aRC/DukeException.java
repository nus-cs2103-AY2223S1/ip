package arc;

/**
 * Encapsulates an exception that is thrown in the Duke program
 */
public class DukeException extends Exception {
    /**
     * Constructor for aRC.DukeException
     * @param message Message to be included when exception is thrown
     */
    public DukeException(String message) {
        super(message
                + Duke.LIST_OF_ARC_COMMANDS);
    }
}
