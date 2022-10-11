package duke.exceptions;

/**
 * {@code FileFailedToLoadException} is an exception thrown when a
 * file fails to load.
 */
public class FileFailedToLoadException extends DukeException {
    /**
     * Constructs a file failed to load exception.
     */
    public FileFailedToLoadException() {
        super("File failed to load!");
    }
}
