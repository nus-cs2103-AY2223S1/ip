package duke.exception;

/**
 * Superclass of all the exceptions specific to the chatBot Duke
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}

