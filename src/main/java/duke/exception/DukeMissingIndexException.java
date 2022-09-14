package duke.exception;

/**
 * An exception class that is thrown when a task command is missing the index.
 */
public class DukeMissingIndexException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeMissingIndexException(String message) {
        super(message);
    }
}
