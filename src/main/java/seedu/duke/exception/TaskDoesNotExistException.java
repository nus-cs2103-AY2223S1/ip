package seedu.duke.exception;

/**
 * Exception class to be used when the task index given does not exist
 */
public class TaskDoesNotExistException extends DukeException {
    public TaskDoesNotExistException(int i) {
        super("There is no task " + Integer.valueOf(i + 1) + " just yet, Master.");
    }
}
