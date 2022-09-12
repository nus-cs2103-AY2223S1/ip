package duke.exception;

/**
 * Invalid date exception when user enter invalid command.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructor for invalid date exception.
     */
    public InvalidDateException() {
        super("OOPS!!! The date you enter should be in YYYY-MM-DD format :-(");
    }
}
