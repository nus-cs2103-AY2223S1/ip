package duke;

/**
 * DukeException is a custom exception class for exceptions when using
 * the Duke program.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for DukeException
     *
     * @param message the error message that is shown to the user
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the String representation of the exception.
     *
     * @return the string that represents the exception
     */
    @Override
    public String toString() {
        return "Oops! " + message;
    }
}
