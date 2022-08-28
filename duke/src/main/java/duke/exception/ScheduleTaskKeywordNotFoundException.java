package duke.exception;

public class ScheduleTaskKeywordNotFoundException extends DukeException{
    public ScheduleTaskKeywordNotFoundException(String command, String keyword) {
        super(command + " command expects a " + keyword + " keyword after specifying the task.");
    }
}
