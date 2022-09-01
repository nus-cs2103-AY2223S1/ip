package duke;

/**
 * Thrown when Duke encounters an issue.
 * @author Lim Ai Lin
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException.
     * @param errorMessage The error message to be shown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
