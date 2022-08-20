package duke.exception;

public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("The description and time limit of deadline cannot be empty");
    }

    public DeadlineException(String error) {
        super(error);
    }
}
