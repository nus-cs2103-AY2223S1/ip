package duke;

/**
 * Exceptions when running the chat bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for <code>DukeException</code>
     * @param error
     */
    public DukeException(String error) {
        super(error);
    }
}
