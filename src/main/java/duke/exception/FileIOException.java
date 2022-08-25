package duke.exception;

/**
 * A general Exception class that encapsulates the unexpected situation that is related to File operation in Duke.
 */
public class FileIOException extends DukeException {
    public FileIOException(String errorMessage) {
        super(errorMessage);
    }
}
