package duke.exception;

public class InvalidDeadlineException extends DukeException {
    private final static String message = "☹ OOPS!!! The description or date of a deadline cannot be empty.";
    public InvalidDeadlineException() {
        super(message);
    }
    public InvalidDeadlineException(String mess) {
        super(mess);
    }
}
