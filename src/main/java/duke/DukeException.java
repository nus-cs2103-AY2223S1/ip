package duke;

/**
 * Exceptions thrown during the execution of the chatbot.
 */
public class DukeException extends RuntimeException {

    /**
     * Class constructor.
     *
     * @param message  the error message
     */
    public DukeException(String message) {
        super(message);
    }
}
