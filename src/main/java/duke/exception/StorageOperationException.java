package duke.exception;

public class StorageOperationException extends FileIOException {
    public StorageOperationException(String errorMessage) {
        super(errorMessage);
    }
}
