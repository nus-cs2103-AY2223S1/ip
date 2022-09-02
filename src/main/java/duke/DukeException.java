package duke;

/**
 * An exception that is only thrown when Duke-specific errors are encountered.
 *
 */
public class DukeException extends Exception {

    private String errorMessage;

    /** Constructor for DukeException */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMsg() {
        return this.errorMessage;
    }

}
