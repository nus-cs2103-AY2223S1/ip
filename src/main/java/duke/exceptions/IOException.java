package duke.exceptions;

public class IOException extends DukeException {
    /**
     * Initialises IOException exception with specified error message.
     *
     * @param errorMessage Message related to error.
     */
    public IOException(String errorMessage) {
        super(errorMessage);
    }
}
