package exceptions;

/**
 * Custom exception only used in chatbot
 */
public class DukeException extends Exception {
    /**
     * Constructs an exception with a specified error message to be displayed
     *
     * @param errorString The specified error message to be displayed.
     */
    public DukeException(String errorString) {
        super(errorString);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
