package duke;

public class DukeException extends RuntimeException {
    DukeException(String message) {
        super(message);
    }

    DukeException() {
        super();
    }
}
