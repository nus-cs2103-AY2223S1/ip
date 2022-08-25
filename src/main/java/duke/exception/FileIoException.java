package duke.exception;

/**
 * A general Exception class that encapsulates the unexpected situation that is related to File operation in Duke.
 */
public class FileIoException extends DukeException {
    public FileIoException(String errorMessage) {
        super(errorMessage);
    }
}
