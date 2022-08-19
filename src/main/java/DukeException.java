/**
 * Class that represents errors exclusive to the Duke chat bot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class DukeException extends Exception {
    /** The error message. */
    String errorMessage;

    /**
     * Constructor for DukeException.
     *
     * @param errorMessage The error message that will be printed out.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Returns the String representation of the error message.
     *
     * @return Returns the String representation of the error message.
     */
    @Override
    public String toString() {
        return errorMessage;
    }
}
