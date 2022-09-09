package duke.exception;

/**
 * Throws an exception when index given is out of range.
 */
public class TaskNotFoundException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION = "Task at index ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " cannot be found!";
    /**
     * Throws an error messgae when index given is out of range.
     *
     * @param index index entered by the user that is out of range.
     */
    public TaskNotFoundException(int index) {
        super(EXCEPTION_FRONT_DESCRIPTION + index + EXCEPTION_BACK_DESCRIPTION);
    }
}
