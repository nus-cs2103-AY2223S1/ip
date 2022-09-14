package duke.exceptions;

/**
 * Representation of an exception where task to be marked already marked.
 */
public class TaskMarkedException extends DukeException {

    /**
     * Informs user that task that specified to be marked
     * is already marked.
     */
    public TaskMarkedException(int message) {
        super("TASK " + message + " ALREADY MARKED!");

    }
}
