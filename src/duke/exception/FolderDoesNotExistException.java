package duke.exception;

public class FolderDoesNotExistException extends FileIOException {
    FolderDoesNotExistException() {
        super("OOPS!!! The selected folder had disappeared.");
    }
}
