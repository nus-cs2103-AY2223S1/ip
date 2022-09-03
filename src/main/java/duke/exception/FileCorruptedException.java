package duke.exception;

/**
 * Represents the exception which occurs when an invalid character is read from a storage file.
 *
 * @author njxue
 * @version v0.2
 */
public class FileCorruptedException extends DukeException {
    /**
     * Returns a FileCorruptedException.
     *
     * @param message Message describing the exception.
     */
    public FileCorruptedException(String message) {
        super(message);
    }
}
