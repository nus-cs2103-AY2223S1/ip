package duke.exceptions;

/**
 * Representation of an exception where task to be unmarked is already unmarked.
 */
public class TaskUnmarkedException extends DukeException {
    public TaskUnmarkedException(int message) {
        super("TASK " + message + " IS ALREADY UNMARKED!");
    }
}
