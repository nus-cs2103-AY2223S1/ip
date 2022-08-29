package duke.exception;

/**
 * DukeFileNotFoundException is a DukeException when file is not found.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class DukeFileNotFoundException extends DukeException {
    /**
     * Constructor for DukeFileNotFoundException.
     */
    public DukeFileNotFoundException() {
        super("File not found, please try again");
    }
}
