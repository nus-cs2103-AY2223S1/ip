package duke.exception;
public class EmptyTaskException extends DukeException {

    public EmptyTaskException() {}

    public EmptyTaskException(String message) {
        super(message);
    }
}
