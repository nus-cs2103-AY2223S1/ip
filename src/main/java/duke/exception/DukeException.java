package duke.exception;

public class DukeException extends Exception {
    public DukeException(String errMsg, Throwable e) {
        super(errMsg, e);
    }
}
