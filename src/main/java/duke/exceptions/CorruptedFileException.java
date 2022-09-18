package duke.exceptions;

/**
 * Represents an Exception due to a corrupted storage file and encapsulates an exception message.
 *
 * @author sikai00
 */
public class CorruptedFileException extends Exception {
    public CorruptedFileException(String msg) {
        super(msg);
    }
}
