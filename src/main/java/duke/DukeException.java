package duke;

/**
 * Exception thrown by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format("\u2639 OOPS!!! %s", message));
    }

    public DukeException(String format, Object... args) {
        super(String.format("\u2639 OOPS!!! %s", String.format(format, args)));
    }
}
