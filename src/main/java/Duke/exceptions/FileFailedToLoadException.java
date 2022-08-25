package Duke.exceptions;

/**
 * {@code FileFailedToLoadException} is an exception thrown when a
 * file fails to load.
 */
public class FileFailedToLoadException extends DukeException {
    /**
     * The constructor for a file failed to load exception.
     */
    public FileFailedToLoadException() {
        super("File failed to load!");
    }
}
