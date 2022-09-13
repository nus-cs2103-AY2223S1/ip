package duke.exceptions;

/**
 * This class encapsulates a DukeException caused when there is no datetime in a task.
 */
public class DukeNoDateTimeException extends DukeException {

    /**
     * Constructs a DukeNoDateTimeException exception.
     */
    public DukeNoDateTimeException() {
        super("Exception: This task does not have a date and time.", "When is this task supposed to happen?");
    }
}
