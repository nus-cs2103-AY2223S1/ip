package duke.exceptions;

/**
 * DukeException child exception specifying custom IOException.
 */
public class InputOutputException extends DukeException {
    /**
     * Initialises IOException exception with specified error message.
     *
     * @param errorMessage Message related to error
     */
    public InputOutputException(String errorMessage) {
        super(errorMessage);
    }
}
