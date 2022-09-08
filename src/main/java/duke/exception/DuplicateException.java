package duke.exception;

public class DuplicateException extends DukeException{
    private static final String errorString = " This task already exists in the task list!";

    public DuplicateException() {
        super(errorString);
    }
}
