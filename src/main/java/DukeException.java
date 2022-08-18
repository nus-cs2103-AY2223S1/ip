/**
 * A DukeException is thrown if there is any error related to Duke the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for a DukeException.
     *
     * @param message the message to be printed when a DukeException is thrown and caught.
     */
    public DukeException(String message) {
        super(message);
    }
}
