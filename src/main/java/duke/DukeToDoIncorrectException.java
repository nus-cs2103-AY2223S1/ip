package duke;

public class DukeToDoIncorrectException extends DukeException {
    public DukeToDoIncorrectException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
