package duke.exception;

/**
 * A class representing no date given exception.
 */
public class NoDateException extends DukeException {
    public NoDateException() {
        super("please specify a date");
    }
}
