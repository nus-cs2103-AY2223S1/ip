package duke;

/**
 * Exception for when Duke's task input/output goes wrong.
 */
public class DukeTaskException extends DukeException {
    DukeTaskException(String message) {
        super(message);
    }
}
