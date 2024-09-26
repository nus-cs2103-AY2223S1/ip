package duke.exception;

/**
 * A class representing no deadline given exception.
 */
public class NoDeadlineException extends DukeException {
    public NoDeadlineException() {
        super("please specify a deadline");
    }
}
