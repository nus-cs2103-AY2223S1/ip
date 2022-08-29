package duke.exception;

/**
 * DukeNoMatchFoundException is a DukeException when the keyword has no matches.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeNoMatchFoundException extends DukeException {
    /**
     * Constructor for DukeNoMatchFoundException.
     *
     * @param keyword The keyword that has no matches.
     */
    public DukeNoMatchFoundException(String keyword) {
        super("Keyword " + keyword + " has no matches found");
    }
}
