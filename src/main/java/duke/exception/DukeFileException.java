package duke.exception;

/**
 * Creates an exception that is thrown when the file storage fails to read or write.
 */
public class DukeFileException extends DukeException {
    public DukeFileException(String errorMsg) {
        super(errorMsg);
    }
}
