package duke;

public class DukeNonPositiveIndexException extends DukeException {
    DukeNonPositiveIndexException() {
        super("Index should be positive.");
    }
}
