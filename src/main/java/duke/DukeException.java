package duke;

/**
 * Exception for all errors related to Duke.
 */
public class DukeException extends RuntimeException {

    /**
     * Initializes a DukeException object.
     *
     * @param errorMessage The message to be printed after encountering an error
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
