package duke.exception;

public class TaskDuplicatedException extends DukeException {
    public static final String EXCEPTION_DESCRIPTION = "Task has already been recorded!";
    public TaskDuplicatedException() {
        super(EXCEPTION_DESCRIPTION);
    }
}
