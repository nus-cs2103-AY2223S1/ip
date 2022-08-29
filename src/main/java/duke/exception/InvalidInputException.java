package duke.exception;

/**
 * Represents an exception when input is unable to be parsed
 */
public class InvalidInputException extends Exception {
    public InvalidInputException() {
        super("I do not recognise this command :(\n");
    }
}
