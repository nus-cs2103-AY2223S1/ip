package duke.exception;

/**
 * An Exception class that encapsulates the situation where the certain syntax is not as desired.
 */
public class IllegalSyntaxException extends DukeException {
    public IllegalSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
