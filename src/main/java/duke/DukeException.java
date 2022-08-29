package duke;

/**
 * Exception class specific to Duke chatbot.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for new Duke exception.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
