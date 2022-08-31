package duke;

public class InvalidIndexException extends DukeException {

    public InvalidIndexException() {
        super("OOPS!!! Invalid index, please enter a valid task index.");
    }
}
