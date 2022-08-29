package duke.exception;

/**
 * DukeMissingSpecifierException is a DukeException when a specifier is missing.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeMissingSpecifierException extends DukeException {
    /**
     * Constructor for DukeMissingSpecifierException.
     *
     * @param command The command with wrong specifier.
     * @param specifier The correct specifier.
     */
    public DukeMissingSpecifierException(String command, String specifier) {
        super("It seems like you are missing a specifier for command " + command + ". Please try again with" +
                specifier);
    }
}
