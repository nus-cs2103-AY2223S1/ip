package duke.exception;

/**
 * A class that encapsulates the exceptions caused by an invalid input
 * by the user from the Duke program.
 */
public class InvalidInput extends DukeException {
    /**
     * The class constructor.
     *
     * @param message The error message.
     */
    public InvalidInput(String message) {
        super("Invalid Input! " + message);
    }
}
