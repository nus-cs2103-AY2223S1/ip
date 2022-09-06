package duke.common.exceptions;

/**
 * Represents an exception for insufficient user input.
 */
public class MissingUserInputException extends DukeException {
    /**
     * Constructor for a MissingUserInputException.
     * @param message The error message to inform the user that more details are needed.
     */
    public MissingUserInputException(String message) {
        super(message);
    }
}
