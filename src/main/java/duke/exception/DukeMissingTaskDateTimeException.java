package duke.exception;

/**
 * An exception class that is thrown when a task command is missing the date and time it requires.
 */
public class DukeMissingTaskDateTimeException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeMissingTaskDateTimeException(String message) {
        super(message);
    }
}
