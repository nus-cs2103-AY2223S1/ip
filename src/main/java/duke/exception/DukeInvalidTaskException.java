package duke.exception;

/**
 * Thrown when Duke encounters an invalid task.
 *
 * @author Lim Ai Lin
 */
public class DukeInvalidTaskException extends DukeException{

    /**
     * Creates a DukeInvalidTaskException.
     */
    public DukeInvalidTaskException() {
        super("RAWR! Invalid task.");
    }
}
