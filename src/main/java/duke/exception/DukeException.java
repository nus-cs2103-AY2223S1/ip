package duke.exception;

/**
 * A duke.exception.DukeException is thrown if there is any error related to duke.Duke the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for a duke.exception.DukeException.
     *
     * @param message the message to be printed when a duke.exception.DukeException is thrown and caught.
     */
    public DukeException(String message) {
        super(message);
    }
}
