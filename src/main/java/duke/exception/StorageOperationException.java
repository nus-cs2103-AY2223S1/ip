package duke.exception;

/**
 * An Exception class that encapsulates the unexpected situation that is related to file storage operation.
 * //TODO merge with FileIO
 */
public class StorageOperationException extends FileIOException {
    public StorageOperationException(String errorMessage) {
        super(errorMessage);
    }
}
