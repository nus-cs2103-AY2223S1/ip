package duke.exception;

/**
 * Child Exception caters to Command without description.
 */
public class EmptyTaskException extends DukeException {

    public EmptyTaskException() {}

    public EmptyTaskException(String message) {
        super(message);
    }
}
