package duke;

/**
 * Encapsulates a Duke Exception for trying to add an invalid Deadline.
 *
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Please add a deadline using the command: 'deadline *name* /by *YYYY-MM-DD*'");
    }
}
