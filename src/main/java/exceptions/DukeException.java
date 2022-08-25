package exceptions;

/**
 * Custom exception only used in chatbot
 */
public class DukeException extends Exception {
    /**
     * Takes some errorString which will be printed to console if thrown
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
