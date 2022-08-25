package duke.exception;

/**
 * An Exception class that encapsulates the situation where a specific file required does not exist.
 */
public class FileDoesNotExistException extends FileIOException {
    public FileDoesNotExistException() {
        super("OOPS!!! The saved list had disappeared.");
    }
}
