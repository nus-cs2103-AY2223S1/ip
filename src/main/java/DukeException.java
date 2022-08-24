/**
 * Custom exception only used in Duke chatbot
 */
public class DukeException extends Exception {
    /**
     * A DukeException will take some errorString which will be printed to
     * the console if thrown
     *
     * @param errorString The specified error message to be displayed.
     */
    DukeException(String errorString) {
        super(errorString);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
