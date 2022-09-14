package duke;

/**
 * Exceptions thrown during the execution of the chatbot.
 *
 * @author Sun Ruoxin
 */
public class DukeException extends RuntimeException {

    /**
     * Class constructor.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super(message);
    }
}
