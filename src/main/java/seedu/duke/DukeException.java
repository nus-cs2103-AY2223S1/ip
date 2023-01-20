package seedu.duke;

/**
 * Exception to catch any expected exceptions when running Duke.
 */
public class DukeException extends Exception{
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
