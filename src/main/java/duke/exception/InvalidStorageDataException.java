package duke.exception;

/**
 * Represents an exception when storage data is unable to be parsed
 */
public class InvalidStorageDataException extends Exception {
    public InvalidStorageDataException() {
        super("Storage save file contains invalid data\n" +
                "Storage save file has not been loaded\n");
    }
}
