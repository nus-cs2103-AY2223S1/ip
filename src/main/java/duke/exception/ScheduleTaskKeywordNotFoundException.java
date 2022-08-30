package duke.exception;

/**
 * Throws an exception when a keyword required for the command is not found
 */
public class ScheduleTaskKeywordNotFoundException extends DukeException {
    /**
     * Throws an error message when a keyword required for the command is not found.
     * @param command command that requires a keyword.
     * @param keyword keyword required by the command.
     */
    public ScheduleTaskKeywordNotFoundException(String command, String keyword) {
        super(command + " command expects a " + keyword + " keyword after specifying the task.");
    }
}
