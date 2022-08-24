package duke.exceptions;

/**
 * Encapsulates an instance of the exception thrown by the {@link duke.Duke} application.
 *
 * @author Emily Ong Hui Qi
 */
public class DukeException extends Exception{
    private static final String OOPS_MESSAGE = "OOPS!!!";

    /**
     * Creates a new instance of the Exception object.
     *
     * @param message The error message associated with the exception
     */
    public DukeException(String message) {
        super(String.format("%s %s", DukeException.OOPS_MESSAGE, message));
    }
}
