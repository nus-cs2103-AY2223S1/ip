package duke;

/**
 * A duke.DukeException is thrown if there is any error related to duke.Duke the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for a duke.DukeException.
     *
     * @param message the message to be printed when a duke.DukeException is thrown and caught.
     */
    public DukeException(String message) {
        super(message);
    }
}
