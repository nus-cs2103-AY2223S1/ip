package duke.taskmanager.exceptions;

/**
 * Exception that is thrown when there are invalid arguments to a command
 */
public class InvalidArgumentsException extends Exception {
    /**
     * Exception that handles commands that has invalid arguments
     */
    public InvalidArgumentsException() {
        super("You placed invalid arguments!\n");
    }
}
