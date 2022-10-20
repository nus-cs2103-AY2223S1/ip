package duke.exception;

/**
 * A general Exception class that encapsulates the unexpected situation that is related to user input.
 */
public class IllegalInputException extends DukeException {
    public IllegalInputException(String errorMessage) {
        super(errorMessage);
    }
}
