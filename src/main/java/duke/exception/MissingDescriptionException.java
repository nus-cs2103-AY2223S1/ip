package duke.exception;

/**
 * DukeException when a description is missing.
 */
public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String description) {
        super(description);
    }
}
