package duke.exception;

/**
 * DukeException when the time is missing.
 */
public class MissingTimeException extends DukeException {
    public MissingTimeException(String description) {
        super(description);
    }
}
