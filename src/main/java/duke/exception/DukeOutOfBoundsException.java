package duke.exception;

/**
 * DukeMissingSpecifierException is a DukeException when it is out of bounds.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeOutOfBoundsException extends DukeException {
    /**
     * Constructor for DukeOutOfBoundsException
     *
     * @param start The minimum number within bounds.
     * @param end The maximum number within bounds.
     */
    public DukeOutOfBoundsException(int start, int end) {
        super("Your input is out of bounds. Please try a number between " + start + " and " + end + ".");
    }
}
