package duke.exception;

/**
 * A general Exception class that encapsulates all the unexpected scenarios in Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
