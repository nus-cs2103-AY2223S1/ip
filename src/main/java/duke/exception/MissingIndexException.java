package duke.exception;

/**
 * DukeException when an index is missing.
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException(String description) {
        super(description);
    }
}
