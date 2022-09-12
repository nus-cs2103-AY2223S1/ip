package duke.exceptions;

/**
 * Thrown when an invalid number of arguments is given when parsing exception
 */

public class EmptyTaskException extends Exception {
    public EmptyTaskException(String message) {
        super(message);
    }
}
