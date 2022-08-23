package dukeexceptions;

/**
 * Thrown when there is a missing description passed to duke.Duke
 */
public class MissingDescriptionException extends DukeException {
    public MissingDescriptionException(String command) {
        super(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }
}
