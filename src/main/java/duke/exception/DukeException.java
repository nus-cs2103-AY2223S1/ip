package duke.exception;

public class DukeException extends RuntimeException {

    public static final String MSG_OOPS = "☹ OOPS!!! ";

    public DukeException(String message) {
        super(MSG_OOPS + message);
    }
}
