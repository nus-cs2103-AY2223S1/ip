package duke.exceptions;

/**
 * This class encapsulates a DukeException caused by failure in creating a file.
 */
public class DukeCannotCreateFileException extends DukeException {

    /**
     * Constructs a DukeCannotCreateFileException exception.
     */
    public DukeCannotCreateFileException() {
        super("Exception: Unable to create file.", "Oh no, even I can't create any files... I need my brother.");
    }
}
