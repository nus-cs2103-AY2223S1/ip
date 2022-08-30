package duke;
/**
 * Represents the specific Exception to the duke chatBot.
 */
public class DukeException extends Exception {
    /**
     * Constructs the DukeException Object.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
