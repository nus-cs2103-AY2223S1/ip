package duke.exception;

public abstract class DukeException extends Exception {
    protected DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
