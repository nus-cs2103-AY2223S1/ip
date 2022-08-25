package duke.exception;

/**
 * An Exception class that encapsulates the situation where the folder does not exist.
 */
public class FolderDoesNotExistException extends FileIOException {
    public FolderDoesNotExistException() {
        super("OOPS!!! The selected folder had disappeared.");
    }
}
