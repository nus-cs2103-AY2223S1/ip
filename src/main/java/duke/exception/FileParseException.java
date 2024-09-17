package duke.exception;

/**
 * Thrown when an error occurs when loading the save file into Duke.
 */
public class FileParseException extends DukeException {
    public FileParseException(String invalidLine, Throwable e) {
        super(invalidLine, e);
    }
}
