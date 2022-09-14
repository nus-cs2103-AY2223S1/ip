package seedu.duke.exception;

/**
 * General exception class for Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
