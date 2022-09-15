package seedu.duke.exception;

public class TaskDoesNotExistException extends DukeException {

    public TaskDoesNotExistException(int i) {
        super("There is no task " + Integer.valueOf(i + 1) + " just yet, Master.");
    }
}
