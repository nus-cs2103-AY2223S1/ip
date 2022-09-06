package duke.exception;

/**
 * Exception thrown when the program tries to read invalid save data.
 */
public class DukeInvalidSaveDataException extends DukeException {
    public DukeInvalidSaveDataException() {
        super("Tried to read unexpected save data.");
    }
}
