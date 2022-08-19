package DukeException;

public class TaskOutOfBoundException extends DukeException {
    /**
     * The error raised when user wants to mark/unmark unspecified task.
     * @param msg error message.
     */
    public TaskOutOfBoundException(String msg) {
        super(msg + "\nYou may need to enter additional information for this command to be executed. PLease check!");
    }
}