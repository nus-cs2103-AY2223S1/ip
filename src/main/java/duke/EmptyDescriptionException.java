package duke;

/**
 * Encapsulates a EmptyDescriptionException
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Creates the EmptyDescriptionException object.
     */
    public EmptyDescriptionException() {
        super("OOPS!!! The description cannot be empty.");
    }
}