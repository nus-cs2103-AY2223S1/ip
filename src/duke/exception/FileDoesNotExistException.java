package duke.exception;

public class FileDoesNotExistException extends FileIOException {
    FileDoesNotExistException() {
        super("OOPS!!! The saved list had disappeared.");
    }
}
