package duke;

/**
 * Error to be thrown if command is given without the necessary arguments
 */
public class EmptyDescriptionException extends RuntimeException {
    public EmptyDescriptionException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
