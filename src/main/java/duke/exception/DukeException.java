package duke.exception;

/**
 * For exceptions that occur while Duke is running.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg, Throwable e) {
        super(errMsg, e);
    }
}
