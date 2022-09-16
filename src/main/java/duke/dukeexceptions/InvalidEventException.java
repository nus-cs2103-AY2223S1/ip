package duke.dukeexceptions;

/**
 * Encapsulates a Duke Exception for trying to add an invalid Event.
 *
 */
public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Please add a event using the command: 'event *name* /at *YYYY-MM-DD*'");
    }
}

