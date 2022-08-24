package duke.exception;

public class FolderDoesNotExistException extends FileIOException {
    public FolderDoesNotExistException() {
        super("OOPS!!! The selected folder had disappeared.");
    }
}
