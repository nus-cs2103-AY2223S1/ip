package aRC;

/**
 * Encapsulates a DukeException that handles invalid arguments
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructor for aRC.InvalidArgumentException
     */
    public InvalidArgumentException() {
        super("Invalid arguments :-(");
    }
}
