package duke.exception;

public class FileDoesNotExistException extends FileIOException {
    public FileDoesNotExistException() {
        super("OOPS!!! The saved list had disappeared.");
    }
}
