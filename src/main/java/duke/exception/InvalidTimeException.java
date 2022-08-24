package duke.exception;

/**
 * InvalidTimeException is thrown when the input time format is wrong
 */
public class InvalidTimeException extends DukeExceptions {

    public InvalidTimeException(String msg) {
        super(msg);
    }
}
