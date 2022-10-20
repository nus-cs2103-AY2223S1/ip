package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for adding an event task.
 */
public class InvalidEventTaskException extends DukeException {

    public InvalidEventTaskException(String errorMessage) {
        super(errorMessage);
    }
}
