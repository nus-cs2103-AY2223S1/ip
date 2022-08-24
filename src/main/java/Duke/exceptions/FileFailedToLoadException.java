package Duke.exceptions;

/**
 * {@code FileFailedToLoadException} is an exception thrown when a
 * file fails to load.
 */
public class FileFailedToLoadException extends DukeException {
    /**
     * This exception is thrown when a file fails to load.
     * This occurs when reading a file to load task into duke.
     */
    public FileFailedToLoadException() {
        super("File failed to load!");
    }
}
