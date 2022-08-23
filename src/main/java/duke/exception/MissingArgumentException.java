package duke.exception;

/**
 * DukeException when an argument is missing.
 */
public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String description) {
        super(description);
    }
}
