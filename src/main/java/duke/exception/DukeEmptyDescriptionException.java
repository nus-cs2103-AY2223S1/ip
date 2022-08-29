package duke.exception;

/**
 * DukeEmptyDescriptionException is an DukeException when a command is without a description.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeEmptyDescriptionException extends DukeException {
    /**
     * Constructor for DukeEmptyDescriptionException.
     *
     * @param command Command that is without description.
     */
    public DukeEmptyDescriptionException(String command) {
        super("Please add a description for the command " + command + ".");
    }
}
