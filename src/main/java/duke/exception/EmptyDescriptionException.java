package duke.exception;

/**
 * An Exception class that encapsulates the situation where the user did not include the description of a command.
 */
public class EmptyDescriptionException extends IllegalInputException {
    public EmptyDescriptionException() {
        super("OOPS!!! The description of a command cannot be empty.");
    }
}
