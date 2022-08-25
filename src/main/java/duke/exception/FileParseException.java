package duke.exception;

public class FileParseException extends DukeException {
    public FileParseException(String invalidLine, Throwable e) {
        super(invalidLine, e);
    }
}
