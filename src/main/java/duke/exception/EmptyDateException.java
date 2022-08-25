package duke.exception;

/**
 * An Exception class that encapsulates the situation where user did not indicate date when he/she supposed to.
 */
public class EmptyDateException extends IllegalInputException {
    public EmptyDateException() {
        super("OOPS!!! You have not entered the date for this task.");
    }
}
