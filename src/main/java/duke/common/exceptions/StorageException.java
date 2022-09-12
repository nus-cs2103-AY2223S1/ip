package duke.common.exceptions;

/**
 * Represents an exception for errors when saving or loading data files.
 */
public class StorageException extends DukeException {
    /**
     * Constructor for a StorageException.
     */
    public StorageException(String message) {
        super(message);
    }
}
