package duke.exceptions;

/**
 * Representation of an exception where user attempts to
 * sort task in an unspecified manner
 */
public class InvalidOrderException extends DukeException {
    public InvalidOrderException(String message) {
        super(message + " IS NOT A SPECIFIED ORDER");
    }
}
