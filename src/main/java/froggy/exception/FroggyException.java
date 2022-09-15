package froggy.exception;

/**
 * A custom class to handle any exceptions thrown by the Duke program.
 */
public class FroggyException extends Exception {
    /**
     * Creates a DukeException object.
     *
     * @param message The error message.
     */
    public FroggyException(String message) {
        super(message);
    }


    /**
     * The toString method to display the output when DukeException.toString() is called.
     */
    @Override
    public String toString() {
        return "OOPS!! " + getMessage();
    }
}
