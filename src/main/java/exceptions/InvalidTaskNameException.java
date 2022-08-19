package exceptions;

public class InvalidTaskNameException extends DukeException {
    public InvalidTaskNameException() {
        super("Please include a valid task name");
    }
}
