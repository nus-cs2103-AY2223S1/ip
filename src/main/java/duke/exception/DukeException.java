package duke.exception;

/**
 * A Duke.DukeException is thrown if there is any error related to Duke.Duke the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for a Duke.DukeException.
     *
     * @param message the message to be printed when a Duke.DukeException is thrown and caught.
     */
    public DukeException(String message) {
        super(message);
    }
}
