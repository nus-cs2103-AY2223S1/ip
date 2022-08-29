package duke.exception;

/**
 * DukeException is an DukeException when the command is invalid.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Constructor for DukeInvalidCommandException.
     *
     * @param command The invalid command.
     */
    public DukeInvalidCommandException(String command) {
        super("Command " + command + " not found. Please try again.");
    }
}
