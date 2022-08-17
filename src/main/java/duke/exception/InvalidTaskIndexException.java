package duke.exception;

public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(int taskIndex) {
        super(taskIndex + " is not a valid task index");
    }
}
