package duke;

/**
 * An throwable object that indicates the action of disallowed operations in Duke
 */
public class DukeException extends Exception {

    /**
     * Creates an exception based on a String message which describes a disallowed operation in Duke.
     * @param err String message of disallowed operation.
     */
    public DukeException(String err) {
        super("OOPS!!! " + err);
    }
}
