package duke.exception;

public class InvalidToDoException extends DukeException {
    private final static String message = "☹ OOPS!!! The description of a todo cannot be empty.";
    public InvalidToDoException() {
        super(message);
    }
}
