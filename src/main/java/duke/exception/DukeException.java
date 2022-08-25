package duke.exception;

/**
 * Exceptions related to the Duke bot.
 *
 * @author Bryan Ng Zi Hao
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message The error message to be shown.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Override the toString() method to display the error.
     *
     * @return A String representing error message.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
