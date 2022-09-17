package duke.exception;

public class InvalidTodoTaskException extends DukeException {
    public InvalidTodoTaskException(String errorMessage) {
        super(errorMessage);
    }
}
