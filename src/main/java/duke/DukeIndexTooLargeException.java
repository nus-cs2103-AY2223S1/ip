package duke;

public class DukeIndexTooLargeException extends DukeException {
    DukeIndexTooLargeException() {
        super("Index cannot be larger than list size.");
    }
}
