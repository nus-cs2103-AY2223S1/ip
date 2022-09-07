package duke.exception;

/**
 * A class representing no index specified exception.
 */
public class NoIndexException extends DukeException {
    public NoIndexException() {
        super("Please specify an index");
    }
}
