package duke.exception;

/**
 * Throws an exception when a keyword required for the command is not found
 */
public class ScheduleTaskKeywordNotFoundException extends DukeException {
    public static final String EXCEPTION_FRONT_DESCRIPTION = " command expects a ";
    public static final String EXCEPTION_BACK_DESCRIPTION = " keyword after specifying the task.";
    /**
     * Throws an error message when a keyword required for the command is not found.
     * @param command command that requires a keyword.
     * @param keyword keyword required by the command.
     */
    public ScheduleTaskKeywordNotFoundException(String command, String keyword) {
        super(command + EXCEPTION_FRONT_DESCRIPTION + keyword + EXCEPTION_BACK_DESCRIPTION);
    }
}
