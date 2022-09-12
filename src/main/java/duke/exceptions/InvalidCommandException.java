package duke.exceptions;

/**
 * Thrown when the user attempts to enter an invalid command into the GUI.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
