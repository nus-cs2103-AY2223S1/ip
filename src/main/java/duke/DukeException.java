package duke;

public class DukeException extends Exception {
    public DukeException(String msg, Object... args) {
        super(String.format(msg, args));
    }
}
