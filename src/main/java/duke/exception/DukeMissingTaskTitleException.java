package duke.exception;

/**
 * An exception class that is thrown when a task command is missing its title.
 */
public class DukeMissingTaskTitleException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeMissingTaskTitleException(String message) {
        super(message);
    }
}
