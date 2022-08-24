package duke.exceptions;

/**
 * TODO: Add JavaDocs
 */
public class DukeException extends Exception {
    private static final String MESSAGE_OOPS = "OOPS!!!";

    /**
     * TODO: Add JavaDocs
     */
    public DukeException(String message) {
        super(String.format("%s %s", DukeException.MESSAGE_OOPS, message));
    }
}
