package duke.exception;

/**
 * Throws an exception when index given is out of range.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Throws an error messgae when index given is out of range.
     *
     * @param index index entered by the user that is out of range.
     */
    public TaskNotFoundException(int index) {
        super(String.format("Task at index %d cannot be found", index));
    }
}
