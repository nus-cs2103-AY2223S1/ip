package isara.exception;

/**
 * Class that represents errors exclusive to the duke.Duke chat bot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class IsaraException extends Exception {
    /** The error message. */
    private String errorMessage;

    /**
     * Constructs an instance of DukeException.
     *
     * @param errorMessage The error message that will be printed out.
     */
    public IsaraException(String errorMessage) {
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
