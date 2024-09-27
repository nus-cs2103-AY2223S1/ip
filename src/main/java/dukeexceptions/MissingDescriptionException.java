package dukeexceptions;

/**
 * Thrown when there is a missing description passed to a Task object.
 */
public class MissingDescriptionException extends DukeException {
    /**
     * Constructor for MissingDescriptionException.
     *
     * @param command The name of the Task.
     */
    public MissingDescriptionException(String command) {
        super(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }
}
