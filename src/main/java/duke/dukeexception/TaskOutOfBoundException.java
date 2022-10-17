package duke.dukeexception;

/**
 * The exception raised when user wants to mark/unmark unspecified task.
 */
public class TaskOutOfBoundException extends DukeException {
    /**
     * The exception raised when user wants to mark/unmark unspecified task.
     * @param msg error message.
     */
    public TaskOutOfBoundException(String msg, String suggestion) {
        super(msg + "\nYou may need to enter additional information for this command to be executed. PLease check!\n"
                + suggestion);
    }
}
