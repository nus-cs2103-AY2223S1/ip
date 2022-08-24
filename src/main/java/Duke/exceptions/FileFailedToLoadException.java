package Duke.exceptions;

/**
 * {@code FileFailedToLoadException} is an exception thrown when a
 * file fails to load.
 */
public class FileFailedToLoadException extends DukeException {
    public FileFailedToLoadException() {
        super("File failed to load!");
    }
}
