package duke;

/**
 * An exception that is only thrown when Duke-specific errors are encountered.
 *
 */
public class DukeException extends Exception {

    /** Constructor for DukeException */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
