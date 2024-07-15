package duke;

public class DukeEventIncorrectException extends DukeException {
    public DukeEventIncorrectException() {
        super("OOPS!!! The description of an event cannot be empty/missing.");
    }
}
