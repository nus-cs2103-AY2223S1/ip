package duke.exception;

/**
 * Represents an exception when no description is inputted
 */
public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException() {
        super("Oh no! This command requires a description input!\n");
    }
}
