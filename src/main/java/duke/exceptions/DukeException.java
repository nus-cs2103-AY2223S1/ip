package duke.exceptions;

/**
 * DukeException represents the possible exceptions that might occur while using Duke.
 */
public class DukeException extends RuntimeException {
    private final String MESSAGE;

    /**
     * Constructs a DukeException.
     *
     * @param message Exception message.
     */
    public DukeException(String message) {
        this.MESSAGE = message;
    }

    /**
     * Returns the exception message.
     *
     * @return Exception message.
     */
    @Override
    public String getMessage() {
        return "Sorry something wrong happened!\n\n" + this.MESSAGE;
    }
    
}
