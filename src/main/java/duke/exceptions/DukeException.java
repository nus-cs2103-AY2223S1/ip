package duke.exceptions;

/**
 * TODO: Add JavaDocs
 */
public class DukeException extends Exception {
    private static final String OOPS_MESSAGE = "OOPS!!!";

    /**
     * TODO: Add JavaDocs
     */
    public DukeException(String message) {
        super(String.format("%s %s", DukeException.OOPS_MESSAGE, message));
    }
}
