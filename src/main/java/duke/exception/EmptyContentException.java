package duke.exception;

/**
 * EmptyContentException is thrown when the task content is empty
 */
public class EmptyContentException extends DukeExceptions {
    public EmptyContentException(String msg) {
        super(msg);
    }
}
