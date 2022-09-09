package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there is no number after a command that requires one
 */
public class InvalidIndexException extends NumberFormatException {
    /**
     * Exception that handles commands that requires a number
     */
    public InvalidIndexException() {
        super("You need to put a number after your command!\n");
    }
}
