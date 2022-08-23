package duke.exception;

public class InvalidIndexException extends DukeException {
    private final static String message = "☹ OOPS!!! This item doesn't exist :-(";
    public InvalidIndexException() {
        super(message);
    }
}