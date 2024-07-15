package duke;

public class DukeDeadlineIncorrectException extends DukeException {
    public DukeDeadlineIncorrectException() {
        super("OOPS!!! The description of a deadline cannot be empty/missing.");
    }
}
