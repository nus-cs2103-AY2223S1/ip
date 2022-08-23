package duke;

/**
 * Representation of all exceptions that Duke might throw
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(String.format("☹ OOPS!!! %s", message));
    }

    public DukeException(String format, Object... args) {
        super(String.format("☹ OOPS!!! %s", String.format(format, args)));
    }
}
