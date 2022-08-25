package aRC;

/**
 * Encapsulates a DukeException that handles invalid commands
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for aRC.InvalidCommandException
     */
    public InvalidCommandException() {
        super("I don't know what that means :-(");
    }
}
