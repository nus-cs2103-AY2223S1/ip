package duke.exceptions;

/**
 * This class encapsulates a DukeException caused by failure in accessing a file.
 */
public class DukeCannotOpenFileException extends DukeException {

    /**
     * Constructs a DukeCannotOpenFileException exception.
     */
    public DukeCannotOpenFileException() {
        super("Exception: Unable to access file.", "Oh no, even I can't open any files... I need my brother.");
    }
}
