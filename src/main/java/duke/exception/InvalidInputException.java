package duke.exception;

/**
 * Represents exception for invalid commands that do not belong to any system's command.
 */
public class InvalidInputException extends DukeException {
    private static final String message = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super(message);
    }
}
