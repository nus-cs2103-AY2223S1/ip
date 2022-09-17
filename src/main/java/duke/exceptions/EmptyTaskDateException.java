package duke.exceptions;

/**
 * This exception indicates a missing task date.
 */
public class EmptyTaskDateException extends Exception {

    public EmptyTaskDateException() {
        super("You need to specify a date.");
    }

}
