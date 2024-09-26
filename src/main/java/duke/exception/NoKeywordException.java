package duke.exception;

/**
 * A class representing no keyword exception.
 */
public class NoKeywordException extends DukeException {
    public NoKeywordException() {
        super("Please specify a keyword");
    }
}
